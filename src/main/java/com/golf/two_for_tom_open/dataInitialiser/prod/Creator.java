package com.golf.two_for_tom_open.dataInitialiser.prod;

import java.time.Year;
import java.util.List;

public interface Creator<T> {

    List<T> create(Year year);

}
