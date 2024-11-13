package com.coderkamlesh.coderschool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.coderkamlesh.coderschool.constants.CoderSchoolConstants;
import com.coderkamlesh.coderschool.model.Contact;
import com.coderkamlesh.coderschool.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("contactService")
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public boolean SaveMessageDetails(Contact contact) {
		boolean isSaved = false;

		contact.setStatus(CoderSchoolConstants.OPEN);

		Contact savedContact = contactRepository.save(contact);
		if (null != savedContact && savedContact.getContact_id() > 0) {
			isSaved = true;
		}

		return isSaved;
	}

	public List<Contact> findMsgsWithOpenStatus() {
		List<Contact> contactMsgs = contactRepository.findByStatus(CoderSchoolConstants.OPEN);
		return contactMsgs;
	}
	  public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
	        int pageSize = 5;
	        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
	                sortDir.equals("asc") ? Sort.by(sortField).ascending()
	                        : Sort.by(sortField).descending());
	        Page<Contact> msgPage = contactRepository.findByStatus(
	                CoderSchoolConstants.OPEN,pageable);
	        return msgPage;
	    }


	  public boolean updateMsgStatus(int contactId){
	        boolean isUpdated = false;
	        int rows = contactRepository.updateStatusById(CoderSchoolConstants.CLOSE,contactId);
	        if(rows > 0) {
	            isUpdated = true;
	        }
	        return isUpdated;
	    }

}
