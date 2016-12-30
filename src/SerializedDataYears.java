import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by Agnieszka on 29.12.2016.
 */
public class SerializedDataYears {
    @SerializedName("pola")
    BigDecimal[] pola;

    @SerializedName("dokument_id")
    int dokument_id;

    @SerializedName("rok")
    int rok;
}
