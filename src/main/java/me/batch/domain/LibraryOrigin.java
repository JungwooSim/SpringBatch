package me.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LibraryOrigin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="col_1")
    public String col1;
    @Column(name="col_2")
    public String col2;
    @Column(name="col_3")
    public String col3;
    @Column(name="col_4")
    public String col4;
    @Column(name="col_5")
    public String col5;
    @Column(name="col_6")
    public String col6;
    @Column(name="col_7")
    public String col7;
    @Column(name="col_8")
    public String col8;
    @Column(name="col_9")
    public String col9;
    @Column(name="col_10")
    public String col10;
    @Column(name="col_11")
    public String col11;
    @Column(name="col_12")
    public String col12;
    @Column(name="col_13")
    public String col13;
    @Column(name="col_14")
    public Long col14;
    @Column(name="col_15")
    public Long col15;
    @Column(name="col_16")
    public Long col16;
    @Column(name="col_17")
    public Long col17;
    @Column(name="col_18")
    public String col18;
    @Column(name="col_19")
    public String col19;
    @Column(name="col_20")
    public String col20;
    @Column(name="col_21")
    public Long col21;
    @Column(name="col_22")
    public Long col22;
    @Column(name="col_23")
    public Long col23;
    @Column(name="col_24")
    public Float col24;
    @Column(name="col_25")
    public Float col25;
    @Column(name="col_26")
    public LocalDate col26;
    @Column(name="col_27")
    public Long col27;
    @Column(name="col_28")
    public String col28;
}
/*
도서관명	시도명	시군구명	도서관유형	휴관일	평일운영시작시각	평일운영종료시각	토요일운영시작시각	토요일운영종료시각	공휴일운영시작시각	공휴일운영종료시각	열람좌석수	자료수(도서)	자료수(연속간행물)	자료수(비도서)	대출가능권수	대출가능일수	소재지도로명주소	운영기관명	도서관전화번호	부지면적	건물면적	홈페이지주소	위도	경도	데이터기준일자	제공기관코드	제공기관명
 */
