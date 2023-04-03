package mil.escudo;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.ByteArrayInputStream;

public class Controller {

    @FXML
    private ImageView imageView;

    private VideoCapture capture;

    public void initialize() {
        try {
            // Carga la biblioteca OpenCV
            System.out.println("1");
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            System.out.println("2");
            // Crea el objeto VideoCapture para acceder a la cámara
            capture = new VideoCapture(0);
            System.out.println("3");
            // Si la cámara no está disponible, detiene la aplicación
            if (!capture.isOpened()) {
                System.out.println("4");
                System.err.println("Error: No se pudo acceder a la cámara.");
                System.exit(1);
                System.out.println("5");
            }
            System.out.println("6");
            // Crea un hilo para leer cuadros de video de la cámara y mostrarlos en el ImageView
            new Thread(() -> {
                System.out.println("7");
                while (true) {
                    System.out.println("8");
                    // Lee un cuadro de video de la cámara
                    Mat frame = new Mat();
                    System.out.println("9");
                    capture.read(frame);
                    System.out.println("10");
                    // Convierte la matriz de OpenCV a una imagen de JavaFX
                    Image image = mat2Image(frame);
                    System.out.println("11");
                    // Actualiza el ImageView con la imagen
                    imageView.setImage(image);
                    System.out.println("12");
                }
            }).start();
            System.out.println("13");
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    private Image mat2Image(Mat frame) {
        // Convierte la matriz de OpenCV a una imagen de JavaFX
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }
}
