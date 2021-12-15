# Entrevoisins
## Application completed and tested for the third project of my formation "Android development" with OpenClassrooms 
***
## Table of Contents
1. Technologies and IDE
2. Compile and run the app
3. App description
4. Tests and current status
5. To improve
***
## Technologies and IDE
* Langage : Java
* Frameworks : Butterknife, Gson, Glide, EventBus, Expresso
* IDE : Android Studio
***
## Compile and run the app
* Download the zip folder of the code from this repository
* Extract all its files
* Open Entrevoisins with Android Studio. Note that you need the version 2020.3.1 or newer of the Android Support plugin for IntelliJ IDEA.
* Launch the app on a device with "run 'app'" or shift+F10

***
## App description
This application is developed for Android devices.

Its main activity NeighbourListActivity contains a fragment with a RecyclerView which displays a list of neighbours with their avatars and names. A ViewPager is implemented to swipe between this list and the list of the favorite neighbours.

Each item contains a delete button to remove the concerning neighbour from the neighbour list.

This activity contains an add button which launches AddNeighbourActivity. AddNeighbourActivity displays a random avatar and some TextInputs in order to create a new neighbour by clicking on a button "save".

When an item of the list is clicked, the NeighbourDetailsActivity is launched to display details of this item :

    //itemView navigate to NeighbourDetailsActivity
    holder.mNeighbourName.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Gson gson = new Gson();
            String neighbourJson = gson.toJson(neighbour);

            Intent intent = new Intent(view.getContext(), NeighbourDetailsActivity.class);

            intent.putExtra(NEIGHBOUR, neighbourJson);

            view.getContext().startActivity(intent);
        }
    });

The NeighbourDetailsActivity displays a back button, the avatar, the name, the adress, the phone number, the social adress and the description of the neighbour. It also shows a favorite button which can add the neighbour to the favorite list or remove him from it :

    //FavoriteButton configuration
    @OnClick(R.id.bt_favorite)
    public void addOrRemoveAFavoriteNeighbour() {
        if (mFavoritesNeighboursList.contains(mNeighbour)) {
            mApiService.deleteNeighbour(mNeighbour, true);
            mFavoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_yellow_24dp));
            Toast toast = Toast.makeText(this, getString(R.string.remove_from_favorites), Toast.LENGTH_LONG);
            toast.show();
        } else {
            mApiService.addNeighbour(mNeighbour, true);
            mFavoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
            Toast toast = Toast.makeText(this, getString(R.string.add_to_favorites), Toast.LENGTH_LONG);
            toast.show();
        }
    }
    
***
## Tests and current status
Unit tests and unit instrumented tests have been developed for the main features of the app.
The application can compile and execute and all features of the above description work.

***
## To improve
To improve this app, we should use regular expression to constrain filling of all TextInputs in the AddNeighbourActivity before changing the button "save" to enabled.
We also should automate the launch of the tests when app is run on a device or on the emulator.
