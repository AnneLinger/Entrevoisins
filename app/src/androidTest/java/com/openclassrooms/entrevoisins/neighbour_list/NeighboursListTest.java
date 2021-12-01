
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.activities.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static android.support.test.espresso.action.ViewActions.click;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    //private ListNeighbourPagerAdapter mPagerAdapter;
    //private NeighbourApiService mService;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on a Neighbour, NeighbourDetailsActivity is launched

    @Test
    public void myNeighbourList_clickOnANeighbour_shouldLaunchNeighbourDetailsActivity() {
        //onView(ViewMatchers.withId(R.id.section_label)).perform(ViewActions.click());
        //onView(ViewMatchers.withId(R.id.section_label)).check()

        //onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        //onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DetailViewAction()));
        //intended(hasComponent(NeighbourDetailsActivity.class.getName()));

        //onView(withId(actionOnHolderItem(R.id.item_list_name))).perform(actionOnItemAtPosition(0, click()));
        //onView(ViewMatchers.withId(R.id.item_list_name)).perform(actionOnItemAtPosition(0, click()));
        //intended(hasComponent(NeighbourDetailsActivity.class.getName()));
    }


     * When the ViewPager is on position 1, only favorite neighbours are displayed

    @Test
    public void myNeighbourList_itemOnPositionOne_shouldDisplayFavoriteNeighboursList() {
        //The ViewPager is on position 1
        mPagerAdapter.getItem(1);

        //The neighbour list is not empty
        List<Neighbour> mNeighbourList = mService.getNeighbours(false);
        assertFalse(mNeighbourList.isEmpty());

        //The favorite neighbour list is empty
        List<Neighbour> mFavoriteNeighbourList = mService.getNeighbours(true);
        assertTrue(mFavoriteNeighbourList.isEmpty());

        //The view is empty
        onView(ViewMatchers.withId((R.id.list_neighbours))).check(matches(not(hasDescendant(any(View.class)))));

        /**mNeighbour isn't a favorite neighbour
        Neighbour mNeighbour = mService.getNeighbours(false).get(0);
        List<Neighbour> mNeighbourList = mService.getNeighbours(false);
        assertFalse(mNeighbourList.contains(mNeighbour));

        //mNeighbour is not displayed
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches());
    }*/
}