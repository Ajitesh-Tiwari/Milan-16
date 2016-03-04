package com.webarch.android.milan;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.MiniDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.webarch.android.milan.Activities.EventActivity;
import com.webarch.android.milan.Fragments.ContactFragment;
import com.webarch.android.milan.Fragments.CreditsFragment;
import com.webarch.android.milan.Fragments.HighlightsFragment;
import com.webarch.android.milan.Fragments.HomeFragment;
import com.webarch.android.milan.Fragments.PatronsFragment;
import com.webarch.android.milan.Fragments.SponsorsFragment;
import com.webarch.android.milan.Fragments.TeamFragment;

import org.w3c.dom.Node;


public class MainActivity extends AppCompatActivity {
    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    boolean doubleBackToExitPressedOnce = false;
    TapBarMenu tapBarMenu;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        //final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460");
        //final IProfile profile2 = new ProfileDrawerItem().withName("Bernat Borras").withEmail("alorma@github.com").withIcon(Uri.parse("https://avatars3.githubusercontent.com/u/887462?v=3&s=460"));

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.navigation_header)
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.crossfade_material_drawer)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Events").withIcon(FontAwesome.Icon.faw_calendar).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Highlights").withIcon(FontAwesome.Icon.faw_star).withIdentifier(3),
                        //new PrimaryDrawerItem().withName("Sponsors").withIcon(FontAwesome.Icon.faw_globe).withIdentifier(4),
                        //new PrimaryDrawerItem().withName("Patrons").withIcon(GoogleMaterial.Icon.gmd_parking).withIdentifier(5),
                        //new PrimaryDrawerItem().withName("Team").withIcon(GoogleMaterial.Icon.gmd_accounts).withIdentifier(6),
                        //new SectionDrawerItem().withName("About Us / Feedback").withDivider(true),
                        new PrimaryDrawerItem().withName("Contact Us").withIcon(GoogleMaterial.Icon.gmd_comments).withIdentifier(7),
                        new PrimaryDrawerItem().withName("Credits").withIcon(GoogleMaterial.Icon.gmd_favorite).withIdentifier(8)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem instanceof Nameable) {
                            FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
                            switch ((int)drawerItem.getIdentifier()){
                                case 1:
                                    ft.replace(R.id.MainFragment, new HomeFragment());
                                    ft.commit();
                                    break;
                                case 2:
                                    Intent intent=new Intent(context, EventActivity.class);
                                    startActivity(intent);
                                    break;
                                case 3:
                                    ft.replace(R.id.MainFragment, new HighlightsFragment());
                                    ft.commit();
                                    break;
                                case 4:
                                    ft.replace(R.id.MainFragment, new SponsorsFragment());
                                    ft.commit();
                                    break;
                                case 5:
                                    ft.replace(R.id.MainFragment, new PatronsFragment());
                                    ft.commit();
                                    break;
                                case 6:
                                    ft.replace(R.id.MainFragment, new TeamFragment());
                                    ft.commit();
                                    break;
                                case 7:
                                    ft.replace(R.id.MainFragment, new ContactFragment());
                                    ft.commit();
                                    break;
                                case 8:
                                    ft.replace(R.id.MainFragment, new CreditsFragment());
                                    ft.commit();
                                    break;
                            }
                            getSupportActionBar().setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                            //Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                        }
                        //we do not consume the event and want the Drawer to continue with the event chain
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();
        //get the CrossfadeDrawerLayout which will be used as alternative DrawerLayout for the Drawer
        //the CrossfadeDrawerLayout library can be found here: https://github.com/mikepenz/CrossfadeDrawerLayout
        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));
        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = result.getMiniDrawer();
        //build the view for the MiniDrawer
        View view = miniResult.build(this);
        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));
        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });

        /**
         * NOTE THIS IS A HIGHLY CUSTOM ANIMATION. USE CAREFULLY.
         * this animate the height of the profile to the height of the AccountHeader and
         * animates the height of the drawerItems to the normal drawerItems so the difference between Mini and normal Drawer is eliminated
         **/
        /*
        final double headerHeight = DrawerUIUtils.getOptimalDrawerWidth(this) * 9d / 16d;
        final double originalProfileHeight = UIUtils.convertDpToPixel(72, this);
        final double headerDifference = headerHeight - originalProfileHeight;
        final double originalItemHeight = UIUtils.convertDpToPixel(64, this);
        final double normalItemHeight = UIUtils.convertDpToPixel(48, this);
        final double itemDifference = originalItemHeight - normalItemHeight;
        crossfadeDrawerLayout.withCrossfadeListener(new CrossfadeDrawerLayout.CrossfadeListener() {
            @Override
            public void onCrossfade(View containerView, float currentSlidePercentage, int slideOffset) {
                for (int i = 0; i < miniResult.getAdapter().getItemCount(); i++) {
                    IDrawerItem drawerItem = miniResult.getAdapter().getItem(i);
                    if (drawerItem instanceof MiniProfileDrawerItem) {
                        MiniProfileDrawerItem mpdi = (MiniProfileDrawerItem) drawerItem;
                        mpdi.withCustomHeightPx((int) (originalProfileHeight + (headerDifference * currentSlidePercentage / 100)));
                    } else if (drawerItem instanceof MiniDrawerItem) {
                        MiniDrawerItem mdi = (MiniDrawerItem) drawerItem;
                        mdi.withCustomHeightPx((int) (originalItemHeight - (itemDifference * currentSlidePercentage / 100)));
                    }
                }
                miniResult.getAdapter().notifyDataSetChanged();
            }
        });
        */
        result.setSelection(1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(result.getCurrentSelectedPosition()==2)
            result.setSelection(1);
    }
    public void clicked(View v){
        switch (v.getId()){
            case R.id.item1:
                //Toast.makeText(this,"Facebook",Toast.LENGTH_SHORT).show();
                String urlFB = "https://www.facebook.com/srmmilan";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(urlFB));
                startActivity(i1);
                break;
            case R.id.item2:
                //Toast.makeText(this,"YouTube",Toast.LENGTH_SHORT).show();
                String urlYouTube = "https://www.youtube.com/channel/UCEU_k4WGchbs_JbrjaXTruQ";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(urlYouTube));
                startActivity(i2);
                break;
            /*case R.id.item3:
                Toast.makeText(this,"Twitter",Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.item4:
                //Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Milan '16\n"+getResources().getString(R.string.milan_desc)+"\nAvailable on Play Store-\nhttp://tinyurl.com/Webarch-Milan16";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Milan '16");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Milan"));
                break;
        }
        tapBarMenu= (TapBarMenu) findViewById(R.id.tapBarMenu);
        tapBarMenu.toggle();
    }
}