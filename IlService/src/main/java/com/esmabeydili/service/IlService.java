package com.esmabeydili.service;

import com.esmabeydili.exception.IlAlreadyExistException;
import com.esmabeydili.model.Il;
import com.esmabeydili.repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//injectible olması için
@AllArgsConstructor//private değişkeni initialized yapmak için
public class IlService {

    private final IlRepository ilRepository;

    public  List<Il> getIller(String name) {

        if(name == null){
            return ilRepository.findAll();
        }
        else{
            return ilRepository.findAllByName(name);
        }

    }

    public Il createIl(Il newIl) {
       Optional<Il> ilByName= ilRepository.findByName(newIl.getName());
       if(ilByName.isPresent()){

           throw new IlAlreadyExistException("Il already exist by name"+newIl.getName());

       }
        return ilRepository.save(newIl);
    }

    public void deletedIl(String id) {
        ilRepository.deleteById(id);
    }

    public Il getIlById(String id) {//optional da kullanabiliriz
        return ilRepository.findById( id).orElseThrow(() ->new RuntimeException("il bulunamadı"));
    }

    public void updateIl(String id, Il newIl) {
        Il oldIl =getIlById(id);
        oldIl.setName(newIl.getName());
        ilRepository.save(oldIl);
    }
}
