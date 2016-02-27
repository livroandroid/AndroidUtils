package livroandroid.lib.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.livroandroid.androidutils.R;

/**
 * Baseado no Wizard do Android Studio sobre Nav Drawer. Deixei os comentários originais em inglês.
 * <p/>
 * <p/>
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    private static final String TAG = "NavigationDrawerFragment";

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    public static final boolean LOG_ON = true;

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView listView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("temp", "onCreateView CONTAINER : " + container);

        if (mCallbacks != null) {
            NavDrawerListView navView = mCallbacks.getNavDrawerView(this, inflater, container);

            if (navView != null) {
                View view = navView.view;

                if (view == null) {
                    return createDefaultView("The method getNavDrawerView should return a not null View object.");
                }

                listView = (ListView) view.findViewById(navView.listViewId);

                if (listView == null) {
                    return createDefaultView("The ListView with the specified id was not found. Please review the view returned by the method getNavDrawerView.");
                }

                return view;
            }
        } else {
            return createDefaultView("A activity precisa implementar a interface NavigationDrawerCallbacks.");
        }

        return null;
    }

    private View createDefaultView(String s) {
        TextView t = new TextView(getActivity());
        t.setText(s);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    public static class NavDrawerListView {
        View view;
        int listViewId;

        public NavDrawerListView(View view, int listViewId) {
            this.view = view;
            this.listViewId = listViewId;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        log("NavigationDrawerFragment.onViewCreated: " + listView);

        if (mCallbacks != null && listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    log("listView.onItemClick: " + position);
                    selectItem(position, true);
                }
            });

            listView.setAdapter(mCallbacks.getNavDrawerListAdapter(this));

            listView.setItemChecked(mCurrentSelectedPosition, true);
        }

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition, savedInstanceState == null);
    }

    private void log(String s) {
        if (LOG_ON) {
            Log.d("NavigationDrawerFragment", s);
        }
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(DrawerLayout drawerLayout) {

        this.mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        this.mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                log("onDrawerClosed()");

                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                log("onDrawerOpened()");

                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            openDrawer();
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position, boolean call) {
        mCurrentSelectedPosition = position;
        if (listView != null) {
            listView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            closeDrawer();
        }
        if (mCallbacks != null && call) {
            mCallbacks.onNavDrawerItemSelected(this, position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            log("NavigationDrawerFragment.onAttach: " + activity);
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            Log.d(TAG, "Activity must implement NavigationDrawerCallbacks.");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
//        if (mDrawerLayout != null && isDrawerOpen()) {
//
//        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(int title) {
        setActionBarTitle(getString(title));
    }

    @SuppressWarnings("deprecation")
    public void setActionBarTitle(CharSequence title) {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(title);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    public boolean isDrawerClosed() {
        return !isDrawerOpen();
    }

    public void openDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }

    public void closeDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        NavDrawerListView getNavDrawerView(NavigationDrawerFragment navDrawerFrag, LayoutInflater inflater, ViewGroup container);

        ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navDrawerFrag);

        void onNavDrawerItemSelected(NavigationDrawerFragment navDrawerFrag, int position);
    }

    public View setHeaderValues(View navDrawerView, int listViewContainerId, int imgNavDrawerHeaderId, int imgUserUserPhotoId, int stringNavUserName, int stringNavUserEmail) {

        View view = navDrawerView.findViewById(listViewContainerId);

        view.setVisibility(View.VISIBLE);

        ImageView imgUserBackground = (ImageView) view.findViewById(R.id.imgUserBackground);

        if (imgUserBackground == null) {
            return view;
        }

        imgUserBackground.setImageResource(imgNavDrawerHeaderId);

        TextView tUserName = (TextView) view.findViewById(R.id.tUserName);
        TextView tUserEmail = (TextView) view.findViewById(R.id.tUserEmail);

        ImageView imgUserPhoto = (ImageView) view.findViewById(R.id.imgUserPhoto);
        if (imgUserPhoto != null) {
            imgUserPhoto.setImageResource(imgUserUserPhotoId);
        }

        if (tUserName != null && tUserEmail != null) {
            tUserName.setText(stringNavUserName);
            tUserEmail.setText(stringNavUserEmail);
        }

        return view;
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return mDrawerToggle;
    }
}
