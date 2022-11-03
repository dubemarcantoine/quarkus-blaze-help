package org.acme;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import org.acme.entity.Gift;

@EntityView(Gift.class)
public interface GiftView {

    @IdMapping
    Long getId();

    String getName();
}
