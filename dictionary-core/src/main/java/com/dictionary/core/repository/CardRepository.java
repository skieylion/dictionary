package com.dictionary.core.repository;

import com.dictionary.core.domain.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("select c from Card c left join fetch c.slots s left join fetch c.transcriptions t where s.id=:slotId")
    List<Card> findAllBySlotId(@Param("slotId") Long slotId, Pageable pageable);

    @Query(value = "select distinct on(ce.cardid,max(ce.eventDate)) cr.id\n" +
            "from CardEvent ce \n" +
            "left join Card cr\n" +
            "on cr.id=ce.cardid\n" +
            "left join cardandslot cl\n" +
            "on cl.cardid=cr.id\n" +
            "where cr.state=false and cl.slotid=:slotId\n" +
            "group by ce.cardid, cr.id\n" +
            "having EXTRACT(EPOCH FROM (current_date - max(ce.eventDate)))/60>(7195*pow(count(ce.cardid),3)-29475*pow(count(ce.cardid),2)+39470*count(ce.cardid)-17160)\n" +
            "order by max(ce.eventDate) asc", nativeQuery = true)
    List<Long> findStudentIds(@Param("slotId") Long slotId);

    @Query("select c from Card c where c.id in :ids")
    List<Card> findStudentCardByIds(@Param("ids") List<Long> ids, Pageable pageable);

//    @Query("select cr from Card cr left join cr.slots sl left join cr.cardRemembers rem where sl.id = :slotId and rem.")
//    List<Card> findDeadlineCardsBySlotId(@Param("slotId") Long slotId);


//    @Query("select cr\n" +
//            "from Card cr\n" +
//            "left join cr.slots cl\n" +
//            "where\n" +
//            "\tcl.id=:slotId\n" +
//            "\tand cr.state=false\n" +
//            "\tand cr.id not in (\n" +
//            "\t\tselect cr2.id from CardEvent ce left join ce.card cr2\n" +
//            "\t)")
//    List<Card> findStudentNewCardsBySlotId(@Param("slotId") Long slotId, Pageable pageable);


}
