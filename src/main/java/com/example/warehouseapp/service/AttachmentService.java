package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Attachment;
import com.example.warehouseapp.entity.AttachmentContent;
import com.example.warehouseapp.repository.AttachmentContentRepository;
import com.example.warehouseapp.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    final
    AttachmentRepository attachmentRepository;
    final
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public List<UUID> upload(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        List<UUID> photoIds = new ArrayList<>();
        while (fileNames.hasNext()) {
            String name = fileNames.next();
            List<MultipartFile> multipartFiles = request.getFiles(name);
            for (MultipartFile multipartFile : multipartFiles) {
                Attachment attachment = new Attachment();
                attachment.setName(multipartFile.getOriginalFilename());
                attachment.setContentType(multipartFile.getContentType());
                attachment.setSize(multipartFile.getSize());
                Attachment save = attachmentRepository.save(attachment);

                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(save);
                attachmentContent.setBytes(multipartFile.getBytes());
                attachmentContentRepository.save(attachmentContent);
                photoIds.add(save.getId());
            }
        }
        return photoIds;
    }

    @SneakyThrows
    public void download(UUID id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (optionalAttachment.isPresent() && optionalAttachmentContent.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setContentType(attachment.getContentType());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "file");

            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(optionalAttachmentContent.get().getBytes());
        }
    }
}
