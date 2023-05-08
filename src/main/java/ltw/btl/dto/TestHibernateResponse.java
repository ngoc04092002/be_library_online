package ltw.btl.dto;


import ltw.btl.model.TestHibernateEntity;

public record TestHibernateResponse(Long id, String nameTest) {
    public TestHibernateResponse(TestHibernateEntity hibernateEntity){
        this(hibernateEntity.getId(),hibernateEntity.getNameTest());
    }
}
