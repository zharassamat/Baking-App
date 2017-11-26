package kz.alashalemi.bakingapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by zharas on 11/20/17.
 */

public class BakingContract {

    public static final String AUTHORITY = "kz.alashalemi.bakingapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FOODS = "foods";


    public static final class BakingEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FOODS).build();

        public static final String TABLE_FOOD = "food";
        public static final String TABLE_INGREDIENT = "ingredient";
        public static final String TABLE_STEP = "step";

        public static final String COLUMN_FOOD_ID = "food_id";
        public static final String COLUMN_FOOD_SERVINGS = "food_servings";
        public static final String COLUMN_FOOD_NAME = "food_name";
        public static final String COLUMN_FOOD_IMAGE = "food_image";

        public static final String COLUMN_INGREDIENT_QUANTITY = "ingredient_quantity";
        public static final String COLUMN_INGREDIENT_INGREDIENT = "ingredient_ingredient";
        public static final String COLUMN_INGREDIENT_MEASURE = "ingredient_measure";

        public static final String COLUMN_STEP_STEP = "step_step";
        public static final String COLUMN_STEP_DESCRIPTION = "step_description";
        public static final String COLUMN_STEP_SHORT_DESCRIPTION = "step_shortDescription";
        public static final String COLUMN_STEP_THUMBNAIL = "step_thumbnail";
        public static final String COLUMN_STEP_VIDEO = "step_video";

    }

}
