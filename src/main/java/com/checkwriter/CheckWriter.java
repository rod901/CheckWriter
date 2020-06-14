package main.java.com.checkwriter;

import java.util.Objects;

public class CheckWriter {

    public String convertNumberToWords(String input, int len) {
        Objects.requireNonNull(input);
        // Return error message if greater than 5
        if (len > 5) {
            return "Length can only be a maximum of 5 numbers. i.e. 10000";
        }

        StringBuilder words = new StringBuilder();
        String result;
        // Converts all numbers to word form
        for (char digit: input.toCharArray()) {
            words.append(convertDigitToWord(digit)).append(" ");
        }

        switch (len) {
            case 5:
                // ten thousandths place
                result = getDescriptors(getTenThousands(words.toString()), len);
                break;
            case 4:
                // thousands place
                result = getDescriptors(getThousands(words.toString()), len);
                break;
            case 3:
                // hundreds place
                result = getDescriptors(getHundreds(words.toString()), len);
                break;
            case 2:
                // tens place
                result = getDescriptors(getTens(words.toString()), len);
                break;
            default:
                // ones place
                result = words.toString();
                break;
        }
        return result;
    }

    public int countLengthBeforeDecimal(String input) {
        Objects.requireNonNull(input);
        int result;

        if (input.contains(".")) {
            String[] array = input.split("\\.");
            result = array[0].length();
        } else {
            result = input.length();
        }
        return result;
    }

    public void convertDollars(String input) {
        int len = countLengthBeforeDecimal(input);
        String convertedNum = convertNumberToWords(input, len);
        System.out.println(convertedNum);
    }

    public String getTens(String input) {
        String result = "";
        String[] split = input.split(" ");

        if(split[0].equalsIgnoreCase("one") && !split[1].equalsIgnoreCase("zero")) {
            switch (split[1]) {
                    case "one":
                        result = "eleven";
                        break;
                    case "two":
                        result = "twelve";
                        break;
                    case "three":
                        result = "thirteen";
                        break;
                    case "four":
                        result = "fourteen";
                        break;
                    case "five":
                        result = "fifteen";
                        break;
                    case "six":
                        result = "sixteen";
                        break;
                    case "seven":
                        result = "seventeen";
                        break;
                    case "eight":
                        result = "eighteen";
                        break;
                    default:
                        result = "nineteen";
                        break;
                }
        } else {
            switch (split[0]) {
                case "one":
                    result = "ten";
                    break;
                case "two":
                    result = "twenty";
                    break;
                case "three":
                    result = "thirty";
                    break;
                case "four":
                    result = "forty";
                    break;
                case "five":
                    result = "fifty";
                    break;
                case "six":
                    result = "sixty";
                    break;
                case "seven":
                    result = "seventy";
                    break;
                case "eight":
                    result = "eighty";
                    break;
                default:
                    result = "ninety";
                    break;
            }

            if (!split[1].equalsIgnoreCase("zero")) {
                result = result + " " + split[1];
            }
        }
        return result;
    }

    public String getDescriptors(String input, int len) {
        StringBuilder result = new StringBuilder();
        String[] array;

        final String DOLLARS = " dollars";
        switch (len) {
            case 2:
                result.append(input).append(DOLLARS);
               break;
            case 3:
               array = input.split(" ");
                for (int i = 0; i < array.length; i++) {
                    if (i == 0) {
                        result.append(array[i]).append(" hundred");
                    } else {
                        result.append(" ").append(array[i]);
                    }
                }
                result.append(DOLLARS);
                break;
            default:
                array = input.split(" ");
                for (int i = 0; i < array.length; i++) {
                    if (i == 0) {
                        result.append(" ").append(array[i].concat(" thousand"));
                    }else if (i == 1) {
                        result.append(" ").append(array[i].concat(" hundred"));
                    } else {
                        result.append(" ").append(array[i]);
                    }
                }
                result.append(DOLLARS);
                break;
        }
        return result.toString();
    }

    public String getHundreds(String input) {
        String[] split = input.split(" ");
        String result = split[0];

        if (!split[1].equalsIgnoreCase("zero") || !split[2].equalsIgnoreCase("zero")) {
            String out = split[1] + " " + split[2];
            result = result + " " + getTens(out);
        }
        return result;
    }

    public String getThousands(String input) {
        String[] periodSplit = input.split("\\.");
        String[] split = periodSplit[0].split(" ");
        String result = split[0];

        if (!split[1].equalsIgnoreCase("zero") || !split[2].equalsIgnoreCase("zero")
                || !split[3].equalsIgnoreCase("zero")) {
            String out = split[1] + " " + split[2] + " " + split[3];
            result = result + " " + getHundreds(out);
        }
        return result;
    }

    public String getTenThousands(String input) {
        String[] periodSplit = input.split("\\.");
        String[] split = periodSplit[0].split(" ");
        String tensOut = split[0] + " " + split[1];
        String result = getTens(tensOut);

        if (split[2].equalsIgnoreCase("zero")) {
            split[2] = "";
        }

        if (!split[2].equalsIgnoreCase("zero") || !split[3].equalsIgnoreCase("zero")
                || !split[4].equalsIgnoreCase("zero")) {
            String hundredsOut = split[2] + " " + split[3] + " " + split[4];
            result = result + " " + getHundreds(hundredsOut);
        }
        return result;
    }

    public String convertDigitToWord(char input) {
        String digit = String.valueOf(input);

        switch (digit) {
            case "1":
                return "one";
            case "2":
                return "two";
            case "3":
                return "three";
            case "4":
                return "four";
            case "5":
                return "five";
            case "6":
                return "six";
            case "7":
                return "seven";
            case "8":
                return "eight";
            case "9":
                return "nine";
            default:
                return "zero";
        }
    }
}
