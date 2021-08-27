import java.util.Scanner;

public class ConsoleUtil{
    Scanner sc = new Scanner(System.in);
    public static final String ERROR_INPUT = "Error input.";
    
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public static boolean yesTo(Scanner console, String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    
    private void info(String content){
        System.out.println(content);
    }
    
    /**
     * get user input integer
     * @param prompt prompt infomation
     * @return integer which user inputed
     */
    private int inputInt(String prompt)
    {
        System.out.print(prompt + " ");
        int num;
        try{
            num = Integer.parseInt(sc.nextLine());
            return num;
        }catch(Exception e){
            info(ERROR_INPUT);
            return inputInt(prompt);
        }
    }

    /**
     * get user input from console
     * @param prompt prompt infomation
     * @param options options to input
     * @return user input
     */
    private String input(String prompt, String ... options) 
    {
        System.out.print(prompt + " ");
        String response = sc.nextLine().trim();;
        while(options != null && options.length > 0 && !contains(options, response)){
            info(ERROR_INPUT);
            info(prompt);
            response = sc.nextLine().trim();
        }
        return response;
    }

    /**
     * prompt user to choose yes or no
     * @param prompt prompt infomation
     * @return true if user input yes or y
     */
    private boolean yesTo(String prompt)
    {
        String response = input(prompt, "yes", "no", "y", "n");
        return response.equals("yes") || response.equals("y");
    }

    /**
     * check if str is in the options array
     * @param options string array
     * @param str string to check
     * @return true if str is in options
     */
    private boolean contains(String[] options, String str)
    {
        if(options == null || str == null)
        {
            return false;
        }
        for(String option : options)
        {
            if(str.equals(option))
            {
                return true;
            }
        }
        return false;
    }
}