package lab.bookmark;

import java.io.*;
import java.net.URL;

public class Bookmark {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("Bookmarks.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            saveImage(line, getFormat(line) + "/" + getName(line));
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();

        String folder = "src/main/resources/";

        File file = new File(folder + destinationFile);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        file.createNewFile();
        FileOutputStream os = new FileOutputStream(file,false);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    public static String getFormat(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
    }

    public static String getName(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }
}
