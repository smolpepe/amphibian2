package com.nikita.amphibian;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, get_key {
    private int id_for_db;
    public int getId_for_db() {
        return id_for_db;
    }
    public void setId_for_db(int value) {
        id_for_db = value;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        ImageView iv = findViewById(R.id.lizard);
        if (iv != null) {
            iv.setOnTouchListener(this);
        }
        loadFromDBToMemory();
    }
    private void loadFromDBToMemory()
    {
        DatabaseHelper sqLiteManager = DatabaseHelper.instanceOfDatabase(this);
    }
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent ev) {
        boolean handledHere = false;

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        // If we cannot find the imageView, return.
        ImageView imageView = v.findViewById(R.id.lizard);
        if (imageView == null) return false;

        // When the action is Down, see if we should show the "pressed" image for the default image.
        // We do this when the default image is showing. That condition is detectable by looking at the
        // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
        Integer tagNum = (Integer) imageView.getTag();
        int currentResource = (tagNum == null) ? R.drawable.lizard : tagNum;

        // Now that we know the current resource being displayed we can handle the DOWN and UP events.

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                if (currentResource == R.drawable.lizard)
                    handledHere = true;
                break;
            case MotionEvent.ACTION_UP:
                // On the UP, we do the click action.
                // The hidden image (lizard_mask) has four different hotspots on it.
                // The colors are red, blue, yellow and green.
                // Use image_areas to determine which region the user touched.
                int touchColor = getHotspotColor(R.id.lizard_mask, evX, evY);

                // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.

                switch (touchColor) {
                    case Color.RED:
                    case Color.BLUE:
                    case Color.YELLOW:
                    case Color.GREEN:
                        setId_for_db(touchColor);
                        FragmentShow frag0 = new FragmentShow();
                        FragmentTransaction ft0 = getSupportFragmentManager().beginTransaction();
                        ft0.replace(R.id.fragment_placeholder, frag0);
                        ft0.addToBackStack(null);
                        ft0.commit();
                        break;

                }

        } // end switch

        return handledHere;
    }

    private int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = findViewById(hotspotId);
        if (img == null) {
            Log.d("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
}

