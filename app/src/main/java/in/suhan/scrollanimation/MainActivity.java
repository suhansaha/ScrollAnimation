package in.suhan.scrollanimation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private int prevScrollY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ScrollView view = (ScrollView) findViewById(R.id.feedBody);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View imgView = findViewById(R.id.feedThumb);
                int scrollX = view.getScrollX();
                int scrolly = view.getScrollY();

                int height = imgView.getHeight();
                int deltaY = scrolly - prevScrollY;
                prevScrollY = scrolly;

                Log.d("SuhanTrace", scrolly + " , " + height);
                ViewGroup.LayoutParams params = imgView.getLayoutParams();
                //view.scrollTo(0, 900);

                TextView title = (TextView) findViewById(R.id.feedTitle);

                if(scrolly < 300){
                    title.setVisibility(View.VISIBLE);
                    imgView.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle(" ");
                    params.height = height - deltaY;
                    imgView.setLayoutParams(params);
                    getSupportActionBar().show();
                    imgView.setAlpha((float) (560 - scrolly) / 560);
                    toolbar.setBackgroundColor(Color.argb(1,0,0,0));

                }else if (scrolly < 500) {
                    getSupportActionBar().setTitle(title.getText());
                    toolbar.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                    title.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    getSupportActionBar().show();
                }else if (scrolly < 600) {
                    getSupportActionBar().hide();
                }
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
