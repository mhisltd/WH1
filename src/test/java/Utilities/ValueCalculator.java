package Utilities;
import java.math.BigDecimal;

/**
 * Created by MSH on 14/05/2017.
 * This class is used as a helper class, to perform the independent calculation of return value against the betvalue.
 * Note: The calculation is based on the experience attained during this testing process and
 * is not based on the business knowledge.
 */
public class ValueCalculator {

    // Method calculates the Return Value. (This calculation is based on the understanding and was not part of the AC)
    public static String CalculateReturnValue(String dataOdds, String stakeValue)
    {
        String dataNum = dataOdds.substring(0, dataOdds.indexOf("/"));
        String dataDenom = dataOdds.substring(dataOdds.indexOf("/")+1);
        Double stakeValueInDouble = Double.valueOf(stakeValue);

        BigDecimal rtnValue = BigDecimal.valueOf(((Double.valueOf(dataNum)/Double.valueOf(dataDenom))* stakeValueInDouble)+stakeValueInDouble);

        String calculateValue = rtnValue.toString();
        // Regular expressions used to pick the two decimal place number without any rounding.
        calculateValue = calculateValue.replaceAll("(\\d+\\.\\d{2})\\d*", "$1");
        return calculateValue;
    }
}
