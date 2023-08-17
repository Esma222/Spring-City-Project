package com.esmabeydili.controller;

import com.esmabeydili.exception.IlAlreadyExistException;
import com.esmabeydili.exception.IlNotFoundException;
import com.esmabeydili.model.Il;
import com.esmabeydili.service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sehirler")
@AllArgsConstructor
public class IlController {
    //private static final List<Il> iller= new ArrayList<>();

    /*//httpstatus bakımından hata verdiğinden kullanmıyoruz
    public List<Il> getIller1(){

        Il il1 = new Il("24","erzincan");
        Il il2 = new Il("34","istanbul");
        Il il3 = new Il("35","izmir");

        return Arrays.asList(il1,il2,il3);

    }*/
    
  /*  public IlController(){

        if(iller.isEmpty()){
            Il il1 = new Il(new Date(),"24","erzincan");
            Il il2 = new Il(new Date(),"34","istanbul");
            Il il3 = new Il(new  Date(),"35","izmir");
            iller.add(il1);
            iller.add(il2);
            iller.add(il3);
        }

    }*/
    private final IlService ilService;//allaragsConstructor durumu bu sorunu çözüyor




    @GetMapping()
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
//
//        Il il1 = new Il("24","erzincan");
//        Il il2 = new Il("34","istanbul");
//        Il il3 = new Il("35","izmir");
//        List<Il> iller = Arrays.asList(il1,il2,il3);

        return new ResponseEntity<>(ilService.getIller(name), OK);//kodu küçültmek için static import kullandık

    }

    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable("id") String ilId){
//         Il result =null;
//        for (int i=0; i<iller.size();i++) {
//             Il il = iller.get(i);
//             if(il.getId().equals(ilId)){
//                 result=il;
//             }
//        }
//        if(result==null){
//            throw new RuntimeException("il bulunamadı");
//        }

        return new ResponseEntity<>(getIlById(ilId) , OK);
    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){
//        newIl.setCreateDate(new Date());
//       iller.add(newIl);

 //        return new ResponseEntity<>(newIl, HttpStatus.CREATED);
        return new ResponseEntity<>(ilService.createIl(newIl), CREATED);

    }

    @PutMapping("/{id}")// put için post+ get diyebiliriz
    public ResponseEntity<Void>  updateIl(@PathVariable String id,@RequestBody Il newIl){
      Il oldIl = getIlById(id);
//      oldIl.setName(newIl.getName());
//      oldIl.setCreateDate(new Date());

      ilService.updateIl(id,newIl);

      return new ResponseEntity<>(OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedIl(@PathVariable String id){
/*
        Il il = getIlById(id);
        iller.remove(il);*/
        ilService.deletedIl(id);
        return new ResponseEntity<>(OK);
    }

    private  Il getIlById(String ilId){
//        return iller.stream().filter(il -> il.getId().equals(ilId)).//for kulanımı yerine
//                findFirst().//ilk eşleşeni eşitle
//                orElseThrow(() ->new RuntimeException("il bulunamadı"));//sonuç bulunmazsa bu ifadeyi bastır

                  return ilService.getIlById(ilId);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
       return  new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(IlAlreadyExistException.class)
    public ResponseEntity<String> handleIlAlreadyExistException(IlAlreadyExistException ex){
        return  new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }


}
