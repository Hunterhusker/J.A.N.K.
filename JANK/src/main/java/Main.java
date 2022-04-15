
import com.jank.Analyzer;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

    // Each person gets their own array list of emails
    static ArrayList<ArrayList<String>> subjectEmails;

    public static void main(String[] args) throws IOException, MessagingException {
        subjectEmails = new ArrayList<>();
        int emailCount = 0;
        int currentSubject = 0;

        Properties props = System.getProperties();
        Session session = Session.getInstance(props, null);

        // The parent directory for all the subjects
        File mainDir = new File(args[0]);

        if (mainDir.exists()) {
            for (File subjectDir : mainDir.listFiles()) {
                subjectEmails.add(new ArrayList<String>());

                for (File emailFile : subjectDir.listFiles()) {
                    // Read in the data into fileData and
                    StringBuffer fileData = new StringBuffer(1000);
                    BufferedReader reader = new BufferedReader(new FileReader(emailFile));
                    char[] buffer = new char[1024];
                    int numRead = 0;

                    while ((numRead = reader.read(buffer)) != -1) {
                        fileData.append(buffer, 0, numRead);
                    }
                    reader.close();


                    MimeMessage message = new MimeMessage(session, new ByteArrayInputStream(fileData.toString().getBytes()));
                    subjectEmails.get(currentSubject).add(removeExcess(message));
                    emailCount++;
                }
                currentSubject++;
            }

            int sum = 0;
            // Sum up the elements in the array
            for (ArrayList<String> l : subjectEmails) {
                sum += l.size();
            }

            System.out.println("Files Read: " + emailCount + " | Array Total: " + sum);

        } else {
            System.out.println("Invalid Directory!");
        }

        if (!subjectEmails.isEmpty()) {
            Analyzer analyzer = new Analyzer(subjectEmails);
            analyzer.runAnalysis();
        }

    }

    // Given a mime message, look for the key word "-----Original Message-----"
    // If it is found, only take the part of the message before that.
    // Destroys reply chains
    public static String removeExcess(MimeMessage original) throws MessagingException, IOException {
        String cleaned = original.getContent().toString();
        String delimiter = "-----Original Message-----";

        int cutoff = cleaned.indexOf(delimiter);

        if (cutoff != -1) {
            cleaned = cleaned.substring(0, cutoff);
        }

        return cleaned;
    }
}
