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
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            // Crea el objeto VideoCapture para acceder a la cámara
            capture = new VideoCapture(0);

            // Si la cámara no está disponible, detiene la aplicación
            if (!capture.isOpened()) {
                System.err.println("Error: No se pudo acceder a la cámara.");
                System.exit(1);
            }

            // Crea un hilo para leer cuadros de video de la cámara y mostrarlos en el ImageView
            new Thread(() -> {
                while (true) {
                    // Lee un cuadro de video de la cámara
                    Mat frame = new Mat();
                    capture.read(frame);

                    // Convierte la matriz de OpenCV a una imagen de JavaFX
                    Image image = mat2Image(frame);

                    // Actualiza el ImageView con la imagen
                    imageView.setImage(image);
                }
            }).start();
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
