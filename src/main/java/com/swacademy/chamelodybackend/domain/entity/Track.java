package com.swacademy.chamelodybackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Track implements Comparable<Track>{
    private Music music;
    private double value;

    @Override
    public int compareTo(Track o) {
        if (Double.compare(o.value, this.value) != 0)
            return Double.compare(this.value, o.value);
        if (Integer.compare(o.music.getPopularity(), this.music.getPopularity()) != 0)
            return Integer.compare(o.music.getPopularity(), this.music.getPopularity());
        return this.music.getId().compareTo(o.music.getId());
    }
}
