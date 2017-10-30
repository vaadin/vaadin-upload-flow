package com.vaadin.ui.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import com.vaadin.flow.demo.ComponentDemoServer;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.util.MessageDigestUtil;
import com.vaadin.router.Route;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tag;
import com.vaadin.ui.Text;
import com.vaadin.ui.common.HtmlComponent;
import com.vaadin.ui.html.Div;
import com.vaadin.ui.html.H2;
import com.vaadin.ui.html.Image;
import com.vaadin.ui.paper.dialog.GeneratedPaperDialog;
import com.vaadin.ui.upload.receivers.MemoryBuffer;
import com.vaadin.ui.upload.receivers.MultiFileMemoryBuffer;

/**
 * View for {@link Upload} demo.
 *
 * @author Vaadin Ltd
 */
@Route("upload")
public class UploadView extends DemoView {

    public static void main(String... args) throws Exception {
        new ComponentDemoServer().startServer();
    }

    @Override
    protected void initView() {
        createSimpleUpload();
        createSimpleMultiFileUpload();
        createFilteredMultiFileUpload();
        createNonImmediateUpload();
        i18nSampleUpload();
    }

    private void createSimpleUpload() {
        Div message = new Div();
        // begin-source-example
        // source-example-heading: Single file upload.
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            Component component = createComponent(event.getMIMEType(),
                    buffer.getInputStream());
            addAndOpen(createDialog("Simple upload", event.getFileName(),
                    component));
        });
        // end-source-example

        addCard("Simple in memory receiver for single file upload", upload,
                message);
    }

    private void createSimpleMultiFileUpload() {
        Div message = new Div();
        // begin-source-example
        // source-example-heading: Multi file upload.
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        // upload.addFinishedListener(
        // event -> System.out.println("Finished event"));
        upload.addSucceededListener(event -> {
            Component component = createComponent(event.getMIMEType(),
                    buffer.getInputStream(event.getFileName()));
            addAndOpen(createDialog("Simple upload", event.getFileName(),
                    component));
        });
        // end-source-example

        addCard("Simple in memory receiver for multi file upload", upload,
                message);
    }

    private void createFilteredMultiFileUpload() {
        Div message = new Div();
        // begin-source-example
        // source-example-heading: Multi file upload.
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAccept("image/jpeg,image/png,image/gif");
        // upload.addFinishedListener(
        // event -> System.out.println("Finished event"));
        upload.addSucceededListener(event -> {
            Component component = createComponent(event.getMIMEType(),
                    buffer.getInputStream(event.getFileName()));
            addAndOpen(createDialog("Simple upload", event.getFileName(),
                    component));
        });
        // end-source-example

        addCard("Image file upload", upload, message);
    }

    private void createNonImmediateUpload() {
        Div message = new Div();
        // begin-source-example
        // source-example-heading: Multi file upload.
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setNoAuto(true);

        upload.addSucceededListener(event -> {
            Component component = createComponent(event.getMIMEType(),
                    buffer.getInputStream(event.getFileName()));
            addAndOpen(createDialog("Simple upload", event.getFileName(),
                    component));
        });
        // end-source-example

        addCard("Manual upload demo", upload, message);
    }

    private void i18nSampleUpload() {
        Div message = new Div();
        // begin-source-example
        // source-example-heading: Single file upload.
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            Component component = createComponent(event.getMIMEType(),
                    buffer.getInputStream());
            addAndOpen(createDialog("Simple upload", event.getFileName(),
                    component));
        });
        // end-source-example
        UploadI18N i18n = new UploadI18N();
        i18n.setDropFiles(
                new UploadI18N.DropFiles().setOne("Перетащите файл сюда...")
                        .setMany("Перетащите файлы сюда..."))
                .setAddFiles(new UploadI18N.AddFiles()
                        .setOne("Выбрать файл").setMany("Добавить файлы"))
                .setCancel("Отменить")
                .setError(new UploadI18N.Error()
                        .setTooManyFiles("Слишком много файлов.")
                        .setFileIsTooBig("Слишком большой файл.")
                        .setIncorrectFileType("Некорректный тип файла."))
                .setUploading(new UploadI18N.Uploading()
                        .setStatus(new UploadI18N.Uploading.Status()
                                .setConnecting("Соединение...")
                                .setStalled("Загрузка застопорилась.")
                                .setProcessing("Обработка файла..."))
                        .setRemainingTime(
                                new UploadI18N.Uploading.RemainingTime()
                                        .setPrefix("оставшееся время: ")
                                        .setUnknown(
                                                "оставшееся время неизвестно"))
                        .setError(new UploadI18N.Uploading.Error()
                                .setServerUnavailable("Сервер недоступен")
                                .setUnexpectedServerError(
                                        "Неожиданная ошибка сервера")
                                .setForbidden("Загрузка запрещена")))
                .setUnits(Stream
                        .of("Б", "Кбайт", "Мбайт", "Гбайт", "Тбайт", "Пбайт",
                                "Эбайт", "Збайт", "Ибайт")
                        .collect(Collectors.toList()));

        upload.setI18n(i18n);

        addCard("Simple in memory receiver for single file upload", upload,
                message);
    }

    private Component createComponent(String mimeType, InputStream stream) {
        if (mimeType.startsWith("text")) {
            String text = "";
            try {
                text = IOUtils.toString(stream, "UTF-8");
            } catch (IOException e) {
                text = "exception reading stream";
            }
            return new Text(text);
        } else if (mimeType.startsWith("image")) {
            Image image = new Image();
            image.getElement().setAttribute("src",
                    new StreamResource("fileName", () -> stream));
            return image;
        }
        Div content = new Div();
        String text = String.format("Mime type: '%s'\nSHA-256 hash: '%s'",
                mimeType, MessageDigestUtil.sha256(stream.toString()));
        content.setText(text);
        return new Div();

    }

    private GeneratedPaperDialog createDialog(String title, String text,
            Component content) {
        GeneratedPaperDialog dialog = new GeneratedPaperDialog();

        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);
        dialog.add(p);
        dialog.add(new H2(title));
        dialog.add(content);

        return dialog;
    }

    private void addAndOpen(GeneratedPaperDialog dialog) {
        getUI().ifPresent(ui -> ui.add(dialog));
        dialog.open();
    }
}
