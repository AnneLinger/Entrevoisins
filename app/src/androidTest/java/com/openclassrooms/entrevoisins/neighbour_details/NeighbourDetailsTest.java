package com.openclassrooms.entrevoisins.neighbour_details;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.activities.NeighbourDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
*Test class for NeighbourDetails
*/
@RunWith(AndroidJUnit4.class)
public class NeighbourDetailsTest {

    private NeighbourDetailsActivity mActivity;
    private NeighbourApiService mService;
    private Neighbour mNeighbour;
    private String mNeighbourName;

    @Rule
    public ActivityTestRule<NeighbourDetailsActivity> mActivityRule = new ActivityTestRule<>(NeighbourDetailsActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        mService = DI.getNewInstanceApiService();
        //mNeighbour = mService.getNeighbours(false).get(0);
        //mNeighbour = new Neighbour(13, "Test", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Ville Test",
                //"+33 6 86 57 90 15", "About me test");
    }

    /**
     * We ensure that TextView is filled with neighbour name
     */
    @Test
    public void neighbourNameIsDisplayedInTextView() {
        onView(ViewMatchers.withId(R.id.tv_name)).check(matches(withText(mService.getNeighbours(false).get(0).getName())));
    }

}
