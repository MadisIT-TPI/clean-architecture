package io.reflectoring.buckpal.stock.adapter.in.web;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockCommand;
import io.reflectoring.buckpal.stock.application.port.in.BuyStockUseCase;
import io.reflectoring.buckpal.stock.domain.Stock.StockId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BuyStockController.class)
class BuyStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuyStockUseCase buyStockUseCase;

    @Test
    void testBuyStock() throws Exception {
        mockMvc.perform(post("/stocks/buy/{accountId}/{companyId}/{amount}",
                        1L, 2L, 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(buyStockUseCase).should()
                .buyStock(eq(new BuyStockCommand(
                        new AccountId(1L),
                        new StockId(2L),
                        3)));
    }
}