package potd.zooplus.com.petoftheday;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.petPicture);
        Picasso.with(getApplicationContext()).load("http://www.tarotfinal.com/wp-content/uploads/2013/03/dudas.jpg").into(imageView);


        //
        Button likeButton = (Button) findViewById(R.id.like);
        Button dislikeButton = (Button) findViewById(R.id.dislike);

//        likeButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Like pressed", Toast.LENGTH_SHORT);
//            }
//        });
//
//        dislikeButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Dislike pressed", Toast.LENGTH_SHORT);
//            }
//        });

    }

    public void likeClicked(View view) {
    }

    public void dislikeClicked(View view) {
    }

    public void takePhoto(View view) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
