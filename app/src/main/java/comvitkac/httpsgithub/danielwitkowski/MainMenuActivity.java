package comvitkac.httpsgithub.danielwitkowski;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import java.util.HashMap;

public class MainMenuActivity extends AppCompatActivity {
    SessionManagment session;
    final static String BASE_SERVER_URL="http://localhost:8080/page_0.json";

    ArrayList<Product> arrayList;
    ListView lv;
    //RecyclerView lv;
    CustomListAdapter adapter;

    /*{
        adapter = new CustomListAdapter(
                getApplicationContext(), R.layout.list_layout, arrayList);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        session =new SessionManagment(getApplicationContext());
        session.checkLogin();
        HashMap<String,String>user=session.getUserDetails();
        String name=user.get(SessionManagment.KEY_NAME);
        String email=user.get(SessionManagment.KEY_EMAIL);
        Toast.makeText(getApplicationContext(),name+" "+email,Toast.LENGTH_LONG).show();

        arrayList = new ArrayList<>();
        lv = findViewById(R.id.listView);
        //lv = (RecyclerView) findViewById(R.id.listView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute(BASE_SERVER_URL);
                //new ReadJSON().execute("http://quocnguyen.16mb.com/products.json");
            }
        });


        Button logout = findViewById(R.id.Logout_button);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                session.logoutUser();
                Intent returnLogin = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(returnLogin);
                finish();
            }
        });
    }
    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("array");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Product(
                            productObject.getString("url"),
                            productObject.getString("title"),
                            productObject.getString("desc")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
             adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.list_layout, arrayList
             );
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
