
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.activities.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.DetailViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Test class for lists of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITE_ITEMS_COUNT = 0;

    private ListNeighbourActivity mActivity;

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
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we click on a Neighbour, NeighbourDetailsActivity is launched
     */
    @Test
    public void myNeighbourList_clickOnANeighbour_shouldLaunchNeighbourDetailsActivity() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DetailViewAction()));
        onView(ViewMatchers.withId(R.id.detail_neighbour)).check(matches(isDisplayed()));
    }

    /**
     * We ensure that TextView is filled with the neighbour name in NeighbourDetailsActivity
     */
    @Test
    public void myNeighbourDetailsActivity_neighbourNameIsDisplayedInTextView() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DetailViewAction()));
        onView(ViewMatchers.withId(R.id.tv_name)).check(matches(withText("Caroline")));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of elements is 11
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When the ViewPager is on position 1, only favorite neighbours are displayed
     */
    @Test
    public void myNeighbourList_viewPagerOnPositionOne_shouldDisplayOnlyFavoriteNeighbours() {
        //When we click on My Neighbours
        onView(ViewMatchers.withText("My neighbours"))
                .perform(ViewActions.click());
        //There are 12 items displayed
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));

        //When we click on Favorites
        onView(ViewMatchers.withText("Favorites"))
                .perform(ViewActions.click());
        //There is no item to display
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .check(withItemCount(FAVORITE_ITEMS_COUNT));
    }
}