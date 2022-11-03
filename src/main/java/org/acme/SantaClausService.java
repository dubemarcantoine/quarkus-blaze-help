package org.acme;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import org.acme.entity.Gift;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SantaClausService {
    @Inject
    @PersistenceUnit(name = "db")
    EntityManager em;
    @Inject
    CriteriaBuilderFactory cbf;
    @Inject
    @PersistenceUnit(name = "db")
    EntityViewManager evm;

    @Transactional
    public List<GiftView> findAllGifts() {
        CriteriaBuilder<Gift> cb = cbf.create(em, Gift.class);
        return evm.applySetting(EntityViewSetting.create(GiftView.class), cb).getResultList();
    }
}
