package com.forensic.mart.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.server.ServerResponse;
import com.google.android.gms.ads.LoadAdError;
import com.forensic.mart.BeanFiles.ProfileDataPostBean;
import com.forensic.mart.BeanFiles.WebPost_bean;
import com.forensic.mart.ChatSystem.ChatScreen;
import com.forensic.mart.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.forensic.mart.SmallFeatures.BottomSheet_comments;
import com.forensic.mart.SmallFeatures.PDFTools;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WebPostAdapter extends RecyclerView.Adapter<WebPostAdapter.InnerViewClassHolder> {
    ArrayList<WebPost_bean.UserBean> aryList;
    Context ctx;
    String userId,usersName;

    public WebPostAdapter(ArrayList<WebPost_bean.UserBean> data, FragmentActivity activity, String UserID,String userName) {
        aryList = data;
        ctx = activity;
        userId = UserID;
        usersName = userName;
    }

    @NonNull
    @Override
    public InnerViewClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_webpost, parent, false);
            //RecyclerViewHolder holder=new RecyclerViewHolder(view);
            return new InnerViewClassHolder(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.layout_books_web_posts, parent, false);
            //RecyclerViewHolder holder=new RecyclerViewHolder(view);
            return new InnerViewClassHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InnerViewClassHolder holder, int position) {
        WebPost_bean.UserBean user = aryList.get(position);

        if (holder.getItemViewType() == 0) {
            final String[] PostHolder_name = new String[1];
            if (user.getType().equals("1")) holder.type.setText("Events");
            else if (user.getType().equals("2")) holder.type.setText("Books");
            else if (user.getType().equals("3")) holder.type.setText("Research Paper");
            else if (user.getType().equals("4")) holder.type.setText("Practice Test");
            else holder.type.setText("Error");

            // loading basic profile info

            SetDataToDatabase con = Conn.doConnect();
            con.profile_data_posts("post_data", user.getUser_id(), new Callback<ProfileDataPostBean>() {
                @Override
                public void success(ProfileDataPostBean profileDataPostBean, Response response) {
                    holder.username.setText(profileDataPostBean.data.get(0).getName());
                    Picasso.with(ctx).load("https://www.forensicmart.com/uploads/" + profileDataPostBean.data.get(0).getPicture()).error(R.drawable.noimage).into(holder.profile);
                    holder.country.setText(profileDataPostBean.data.get(0).getCountry_id());
                    Log.d("----", profileDataPostBean.data.get(0).getCountry_id());
                    PostHolder_name[0] = profileDataPostBean.data.get(0).getName();
                    //not getting response leaving here. for country
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

            if (user.getFile() != null && !user.getFile().contains(".pdf")) {
                Picasso.with(ctx).load("https://www.forensicmart.com/uploads/" + user.getFile()).error(R.drawable.noimage).into(holder.postImage);
            } else if (user.getBlobfile() != null) {
                InputStream is = new ByteArrayInputStream(user.getBlobfile().getBytes()); //stream pointing to your blob or file
                holder.bookImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                holder.bookImage.setAdjustViewBounds(true);
                holder.bookImage.setImageBitmap(BitmapFactory.decodeStream(is));
                Log.d("TAG", "Loading blob file");
            } else {
                holder.postImage.setVisibility(View.INVISIBLE);
                holder.postImage.setVisibility(View.GONE);
            }
            holder.like.setText(user.getLikes());
            holder.date.setText(user.getCreated_at());
            holder.title.setText(user.getTitle());
            holder.details.setText(user.getDetails());

            if(user.getFile()!=null){}

            assert user.getUrl() != null;
            if (user.getUrl() != null) {
                holder.visitLink.setVisibility(View.VISIBLE);
                holder.visitLink.setOnClickListener( v->{
                    try{
                        Uri uriUrl = Uri.parse(user.getUrl());
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                        ctx.startActivity(launchBrowser);
                    }
                    catch (Exception ignored){}
                });

            }
            //sharing post or app
            holder.share.setOnClickListener(v -> {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Forensic Mart");
                    String sAux = "\nLook at this amazing post " + holder.title.getText().toString() + "  \n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.mart.forensic";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    ctx.startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            });

            // checking if user has already liked post
            SetDataToDatabase con1 = Conn.doConnect();
            con1.likes("checklike", user.getId(), userId, new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {

                    if (serverResponse.msg.equalsIgnoreCase("liked")){
                        holder.likeImage.setColorFilter(Color.parseColor("#EA6021"));
                        holder.like.setTextColor(Color.parseColor("#EA6021"));
                    }
                }
                @Override
                public void failure(RetrofitError error) {

                }
            });

            // liking and unliking posts
            holder.likeImage.setOnClickListener(v->{
                SetDataToDatabase con2 = Conn.doConnect();
                con2.likes("like_unlike", user.getId(), userId, new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {

                        if (serverResponse.msg.equalsIgnoreCase("liked")){
                            holder.likeImage.setColorFilter(Color.parseColor("#EA6021"));
                            holder.like.setTextColor(Color.parseColor("#EA6021"));
                            try {
                                holder.like.setText(String.valueOf(Integer.parseInt(holder.like.getText().toString())+1));
                            }catch (Exception ignored){}
                        }
                        else
                            if (serverResponse.msg.equalsIgnoreCase("unliked")){
                                holder.likeImage.setColorFilter(Color.parseColor("#757575"));
                                holder.like.setTextColor(Color.parseColor("#757575"));
                                try {
                                    holder.like.setText(String.valueOf(Integer.parseInt(holder.like.getText().toString())-1));
                                }catch (Exception ignored){}
                            }
                    }
                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            });
            // loading comments
            holder.commentsImage.setOnClickListener(v -> {
                BottomSheet_comments bottomSheetDialog = new BottomSheet_comments((Activity) ctx, userId, user.getId());
                bottomSheetDialog.show(((FragmentActivity) ctx).getSupportFragmentManager(), "bottomSheetDialog");

            });
            // starting chat with specific user or continuing chat
            holder.dropMsg.setOnClickListener(v->{
                Intent intent = new Intent(ctx, ChatScreen.class);
                intent.putExtra("sender_id",userId);
                intent.putExtra("receiver_id",user.getUser_id());
                intent.putExtra("receiver_name", PostHolder_name[0]);
                intent.putExtra("userName", usersName);
                ctx.startActivity(intent);
            });

        }
        // setting content for books
        else if (holder.getItemViewType() == 1) {
            holder.bookTitle.setText(user.getTitle());
            if (user.getDetails() != null) {
                holder.bookDetails.setText(user.getDetails());
            }
            // setting up image for book if pic is not uploaded then url pic will be shown (if url is added)
            if (user.getFile() != null) {
                try {
                    Picasso.with(ctx).load("https://www.forensicmart.com/uploads/" + user.getFile())
                            .fit().into(holder.bookImage);
                } catch (Exception e) {
                    if (user.getUrl() != null) {
                        Picasso.with(ctx).load(R.drawable.url).fit().error(R.drawable.noimage).into(holder.bookImage);
                    } else
                        Picasso.with(ctx).load(R.drawable.noimage).fit().into(holder.bookImage);

                }
                Log.d("TAG", "Loading file file");

            } else if (user.getBlobfile() != null) {
                InputStream is = new ByteArrayInputStream(user.getBlobfile().getBytes()); //stream pointing to your blob or file
                holder.bookImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                holder.bookImage.setAdjustViewBounds(true);
                holder.bookImage.setImageBitmap(BitmapFactory.decodeStream(is));
                Log.d("TAG", "Loading blob file");
            } else if (user.getUrl() != null) {
                Picasso.with(ctx).load(R.drawable.url).fit().error(R.drawable.noimage).into(holder.bookImage);
            } else
                Picasso.with(ctx).load(R.drawable.noimage).fit().into(holder.bookImage);


            if (user.getUrl() != null) {
                Log.d("TAG", "");

            } else {
                holder.viewBook.setVisibility(View.INVISIBLE);
                holder.viewBook.setVisibility(View.GONE);
            }

            if (user.getFile()!=null && user.getFile().contains(".pdf")){
            holder.openBook.setVisibility(View.VISIBLE);
            }

            holder.openBook.setOnClickListener(v->{
                PDFTools.showPDFUrl(ctx,user.getFile());

            });



            InterstitialAd mInterstitialAd;
            mInterstitialAd = new InterstitialAd(ctx);
            mInterstitialAd.setAdUnitId(ctx.getString(R.string.admob_app_id));
            holder.viewBook.setOnClickListener(v -> {
                try {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }

                } catch (Exception e) {
                    Log.d("TAG", e.toString());
                }
                // full screen
            });
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    Uri uriUrl = Uri.parse(user.getUrl());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    ctx.startActivity(launchBrowser);
                }

                @Override
                public void onAdClosed() {
                    // Load the next interstitial.
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    Uri uriUrl = Uri.parse(user.getUrl());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    ctx.startActivity(launchBrowser);
                }
            });

            //sharing post or app
            holder.share.setOnClickListener(v -> {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Forensic Mart");
                    String sAux = "\nLook at this amazing " + holder.title.getText().toString() + "  \n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.mart.forensic";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    ctx.startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }

    public static class InnerViewClassHolder extends RecyclerView.ViewHolder {
        TextView username, type, country, title, details, like, date, bookTitle, bookDetails, comments,visitLink;
        ImageView profile, postImage, likeImage, bookImage, share, commentsImage,dropMsg;
        Button viewBook,openBook;
        //RichLinkView richLinkView;

        public InnerViewClassHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.webpostUserName);
            type = itemView.findViewById(R.id.webpostType);
            date = itemView.findViewById(R.id.webpostDatacreated);
            country = itemView.findViewById(R.id.webpostCountry);
            title = itemView.findViewById(R.id.webpostTitle);
            details = itemView.findViewById(R.id.webpostDetails);
            like = itemView.findViewById(R.id.webpostLikeText);
            postImage = itemView.findViewById(R.id.webpostPostImage);
            likeImage = itemView.findViewById(R.id.webpostLikeImage);
            profile = itemView.findViewById(R.id.webpostImage);
            bookDetails = itemView.findViewById(R.id.author_post_webpost);
            bookTitle = itemView.findViewById(R.id.bookName_post_webpost);
            bookImage = itemView.findViewById(R.id.bookImage_post_webPost);
            viewBook = itemView.findViewById(R.id.view_book_post_webPost);
            openBook = itemView.findViewById(R.id.open_book_post_webPost);
            share = itemView.findViewById(R.id.webpostShare);
            comments = itemView.findViewById(R.id.webpostCommentText);
            commentsImage = itemView.findViewById(R.id.webpostCommentImage);
            //richLinkView = itemView.findViewById(R.id.richLinkView);
            visitLink = itemView.findViewById(R.id.visitlink_webpost);
            dropMsg = itemView.findViewById(R.id.dropMessage_webpost);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (aryList.get(position).getType().equals("2")) {
            return 1;
        } else
            return 0;
    }
}
