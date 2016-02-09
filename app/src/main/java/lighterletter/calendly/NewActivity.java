package lighterletter.calendly;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * Created by c4q-john on 1/21/16.
 */
public class NewActivity extends AppCompatActivity{


  Toolbar mToolbar1;
    Toolbar mToolbar2;
    View mView;
    int cx;
    int cy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_scrolling);
       // overridePendingTransition(R.anim.do_not_move,R.anim.do_not_move);
        setContentView(R.layout.activity_reveal_circular);
        mView = findViewById(R.id.add_activity);
        if (savedInstanceState == null) {
            mView.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = mView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        circularRevealActivity();

                    }
                });
            }
        }

        mToolbar1 = (Toolbar) findViewById(R.id.nested_toolbar_1);
        mToolbar2 = (Toolbar) findViewById(R.id.nested_toolbar_2);
        setSupportActionBar(mToolbar2);
        getSupportActionBar().setTitle("Hello Activity");

    }
    private void circularRevealActivity() {
        cx = mView.getWidth();
        cy = mView.getHeight();
        float finalRadius = Math.max(mView.getWidth(), mView.getHeight());
        // create the animator for this view (the start radius is zero)

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mView, cx, cy, 0, finalRadius);
        circularReveal.setDuration(250);
        // make the view visible and start the animation
        mView.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
