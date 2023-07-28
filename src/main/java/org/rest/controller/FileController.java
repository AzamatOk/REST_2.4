package org.rest.controller;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.rest.model.FileModel;
import org.rest.service.FileService;
import org.rest.service.impl.FileServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class FileController extends HttpServlet {
    private FileService fileService = new FileServiceImpl();
    private Gson gson = new Gson();
    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;

    private String filePath = "D:\\upload\\";
    private File file;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        if (id == null) {
            out.print(gson.toJson(fileService.getAll()));
        } else {
            if (fileService.getById(Integer.parseInt(id)) == null){
                out.print("Error 404");
            }
            else {
                out.print(gson.toJson(fileService.getById(Integer.parseInt(id))));
            }
        }
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();


            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {

                    String fileName = fileItem.getName();
                    fileService.save(new FileModel(null,fileName,filePath));
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);
                    writer.println(fileName + " is uploaded.<br>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        fileService.update(gson.fromJson(req.getReader(), FileModel.class));
        PrintWriter out = resp.getWriter();
        out.println("FileModel was updated" );
        out.flush();
    }
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        fileService.deleteById(Integer.parseInt(id));
        PrintWriter out = resp.getWriter();
        out.println("FileModel was deleted");
        out.flush();
    }
}
