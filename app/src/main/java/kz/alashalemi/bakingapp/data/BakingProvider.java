package kz.alashalemi.bakingapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zharas on 11/20/17.
 */

public class BakingProvider extends ContentProvider{

    public static final int FOODS = 100;
    public static final int FOOD_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private BakingDbHelper mBakingDbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(BakingContract.AUTHORITY, BakingContract.PATH_FOODS, FOODS);
        uriMatcher.addURI(BakingContract.AUTHORITY, BakingContract.PATH_FOODS + "/#", FOOD_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();

        mBakingDbHelper = new BakingDbHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mBakingDbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);

        Cursor returnCursor;

        switch(match) {
            case FOODS:

                returnCursor = db.query(BakingContract.BakingEntry.TABLE_FOOD,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        final SQLiteDatabase db = mBakingDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch(match) {
            case FOODS:

                long id = db.insert(BakingContract.BakingEntry.TABLE_FOOD, null, values);

                if(id > 0) {

                    returnUri = ContentUris.withAppendedId(BakingContract.BakingEntry.CONTENT_URI, id);

                } else {
                    throw new SQLException("Failed to insert row into :" + uri);
                }

                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);


        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
