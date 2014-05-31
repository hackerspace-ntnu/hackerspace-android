package no.ntnu.idi.hackerspace.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Sigurd on 31.05.2014.
 */
public class DoorStatusUpdater extends AsyncTask<String,Void,Boolean> {

    Activity activity;
    public DoorStatusUpdater(Activity activity){
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        JSONObject obj = null;
        boolean open = false;
        try {
            obj = JSONReader.readJsonFromUrl(strings[0]);
            open = obj.getBoolean("isOpen");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return open;
    }

    @Override
    protected void onPostExecute(Boolean open){
        TextView textView = (TextView) activity.findViewById(R.id.textView);
        if(open){
            textView.setText(R.string.door_open);
        }
        else{
            textView.setText("Døra er lukket :(");
        }
    }


}
