import com.google.gson.annotations.SerializedName;

/**
 * Created by Agnieszka on 29.12.2016.
 */
public class SerializedDataYears {
    @SerializedName("pola")
    Double[] pola;

    @SerializedName("dokument_id")
    int dokument_id;

    @SerializedName("rok")
    int rok;
}
