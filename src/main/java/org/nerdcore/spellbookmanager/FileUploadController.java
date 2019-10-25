//package org.nerdcore.spellbookmanager;
//
//import org.nerdcore.spellbookmanager.models.StorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/upload")
//public class FileUploadController {
//
//    private final StorageService storageService;
//
//    @Autowired
//    public FileUploadController(StorageService storageService){
//        this.storageService=storageService;
//    }
//
//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException{
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                        .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }
//
//    @GetMapping("/file/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//
//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file")MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() +"!");
//
//        return "redirect:/upload";
//    }
///*
//    @ExceptionHandler(FileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc){
//        return ResponseEntity.notFound().build();
//    }*/
//
//
//
//
//}
