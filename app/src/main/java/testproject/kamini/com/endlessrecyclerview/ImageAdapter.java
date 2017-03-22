package testproject.kamini.com.endlessrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Kamini on 3/21/2017.
 */

public class ImageAdapter extends
        RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<ImageItem> mImagetem;
    Context context;
    // Pass in the contact array into the constructor
    public ImageAdapter(List<ImageItem> imageitem,Context context) {
        this.context = context;
        mImagetem = imageitem;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView mImageview;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            mImageview = (ImageView) itemView.findViewById(R.id.image);
            //  messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        ImageAdapter.ViewHolder viewHolder = new ImageAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder viewHolder, int position) {
        //ImageItem contact = mImagetem.get(position);

      //  ImageView imageview = viewHolder.mImageview;
        Log.d(TAG, mImagetem.get(position).imageURL+ "me myhhgghghghg");
        Picasso.with(context).load(mImagetem.get(position).imageURL.trim()).resize(200, 200).centerInside().into(viewHolder.mImageview);

       /* Button button = viewHolder.messageButton;

        if (contact.isOnline()) {
            button.setText("Message");
            button.setEnabled(true);
        }
        else {
            button.setText("Offline");
            button.setEnabled(false);
        }*/
    }

    @Override
    public int getItemCount() {
        return mImagetem.size();
    }
}