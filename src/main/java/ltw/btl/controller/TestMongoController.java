package ltw.btl.controller;

import lombok.RequiredArgsConstructor;
import ltw.btl.repository.TestMongoRepo;
import org.springframework.web.bind.annotation.*;
import ltw.btl.model.TestMongo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TestMongoController {
    private final TestMongoRepo testMongoRepo;

    @PostMapping("save-mongo")
    public TestMongo saveTestMongo(@RequestBody TestMongo testMongo){
        return testMongoRepo.save(testMongo);
    }

    @GetMapping("get-mongo")
    public List<TestMongo> getAllTest(){
        return testMongoRepo.findAll();
    }
}
