package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Dto.TotalAmountAllBillDto;
import com.l3azh.bonsai.Dto.TreeQuantityOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticRepository implements IStatisticRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TreeQuantityOrderDto> listTreeQuantityOrderByTheInputDay(int dayNumber) {
        return jdbcTemplate.query("SELECT tree.UUID_Tree,\n" +
                        "       tree.Name,\n" +
                        "       SUM(bdt.Quantity) AS QuantityOrder\n" +
                        "FROM tree\n" +
                        "LEFT JOIN\n" +
                        "  (SELECT bill_detail.*,\n" +
                        "          bill.CreateDate AS BillCreateDate\n" +
                        "   FROM bill_detail\n" +
                        "   LEFT JOIN bill ON bill.UUID_Bill = bill_detail.FK_Bill_UUID_Bill) AS bdt ON tree.UUID_Tree = bdt.FK_Tree_UUID_Tree\n" +
                        "WHERE BillCreateDate >= NOW() - interval ? DAY\n" +
                        "GROUP BY tree.UUID_Tree",
                (rs, rowNum) -> TreeQuantityOrderDto.builder()
                        .uuidTree(rs.getString(1))
                        .nameTree(rs.getString(2))
                        .quantity(rs.getInt(3))
                        .build(),
                dayNumber);
    }

    @Override
    public List<TotalAmountAllBillDto> listTotalBillInLastInputDay(int dayNumber) {
        return jdbcTemplate.query("SELECT temp.CreateDate as TotalBillTime, ROUND(SUM(temp.total),2) as TotalBill\n" +
                        "FROM (SELECT bill.*, (bill_detail.PriceSold*bill_detail.Quantity) as total \n" +
                        "FROM bill LEFT JOIN bill_detail on bill_detail.FK_Bill_UUID_Bill = bill.UUID_Bill) as temp\n" +
                        "WHERE temp.CreateDate >= NOW() - interval ? DAY\n" +
                        "GROUP BY DATE(temp.CreateDate)",
                (rs, rowNum) -> TotalAmountAllBillDto.builder()
                        .totalBillTime(rs.getDate(1))
                        .totalBill(rs.getDouble(2)).build(),
                dayNumber);
    }
}
