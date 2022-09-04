package uz.pdp.springbootexample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootexample.entity.Attachment;
import uz.pdp.springbootexample.entity.User;
import uz.pdp.springbootexample.projection.UserListProjection;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> { }



