package uz.pdp.springbootexample.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.springbootexample.entity.Attachment;
import uz.pdp.springbootexample.entity.AttachmentContent;
import uz.pdp.springbootexample.repository.AttachmentContentRepository;
import uz.pdp.springbootexample.repository.AttachmentRepository;

import java.io.IOException;

@Service
public class AttachmentService {

    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;


    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

     public Attachment saveFile(MultipartFile file){

         try {
            Attachment attachment = Attachment.builder()

                    .fileName(file.getOriginalFilename())
                    .fileFormat(file.getContentType())
                    .size(file.getSize())
                    .build();

               attachmentRepository.save(attachment);
             attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
              return attachment;
         } catch (IOException e) {
             throw new RuntimeException(e);
         }


     }

}
