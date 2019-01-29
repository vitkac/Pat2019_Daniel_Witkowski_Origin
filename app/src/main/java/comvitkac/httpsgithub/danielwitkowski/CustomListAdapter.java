package comvitkac.httpsgithub.danielwitkowski;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> array;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Product> array) {
        super(context, resource, array);
        this.array = array;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_layout, null, true);

        }
        Product array = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imageViewProduct);
        Picasso.with(context).load(array.getUrl()).into(imageView);

        TextView title = convertView.findViewById(R.id.title);
        title.setText(array.getTitle());

        TextView desc = convertView.findViewById(R.id.desc);
        desc.setText(array.getDesc());

        return convertView;
    }
}