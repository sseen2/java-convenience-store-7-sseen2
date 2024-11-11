package store;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    @Test
    void 파일에_있는_상품_목록_출력() {
        assertSimpleTest(() -> {
            run("[물-1]", "N", "N");
            assertThat(output()).contains(
                "- 콜라 1,000원 10개 탄산2+1",
                "- 콜라 1,000원 10개",
                "- 사이다 1,000원 8개 탄산2+1",
                "- 사이다 1,000원 7개",
                "- 오렌지주스 1,800원 9개 MD추천상품",
                "- 오렌지주스 1,800원 재고 없음",
                "- 탄산수 1,200원 5개 탄산2+1",
                "- 탄산수 1,200원 재고 없음",
                "- 물 500원 10개",
                "- 비타민워터 1,500원 6개",
                "- 감자칩 1,500원 5개 반짝할인",
                "- 감자칩 1,500원 5개",
                "- 초코바 1,200원 5개 MD추천상품",
                "- 초코바 1,200원 5개",
                "- 에너지바 2,000원 5개",
                "- 정식도시락 6,400원 8개",
                "- 컵라면 1,700원 1개 MD추천상품",
                "- 컵라면 1,700원 10개"
            );
        });
    }

    @Test
    void 수량_변경_확인() {
        assertSimpleTest(() -> {
            run("[물-1]", "N", "Y", "[물-1]", "N", "N");
            assertThat(output()).contains(
                    "- 물 500원 10개",
                    "- 물 500원 9개"
            );
        });
    }

    @Test
    void 일반_상품_멤버십_할인() {
        assertSimpleTest(() -> {
            run("[물-2],[정식도시락-2]", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains("멤버십할인-4,140");
        });
    }

    @Test
    void 여러_개의_일반_상품_구매() {
        assertSimpleTest(() -> {
            run("[비타민워터-3],[물-2],[정식도시락-2]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains("내실돈18,300");
        });
    }

    @Test
    void 프로모션_재고_확인() {
        assertSimpleTest(() -> {
            run("[콜라-5],[오렌지주스-2],[사이다-9]", "Y", "Y", "N", "N");
            assertThat(output().replaceAll("\\s", ""))
                    .contains("행사할인-5,800","내실돈12,800");
        });
    }

    @Test
    void 기간에_해당하지_않는_프로모션_적용() {
        assertNowTest(() -> {
            run("[감자칩-2]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains("내실돈3,000");
        }, LocalDate.of(2024, 2, 1).atStartOfDay());
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("[컵라면-12]", "N", "N");
            assertThat(output()).contains("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 형식_오류_예외_테스트() {
        assertSimpleTest(() -> {
            runException("컵라면-12", "N", "N");
            assertThat(output()).contains("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 올바르지_않은_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("[콜라-3]", "yes", "N");
            assertThat(output()).contains("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 재고에_없는_상품_주문() {
        assertSimpleTest(() -> {
            runException("[햄버거-1]", "N", "N");
            assertThat(output()).contains("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 프로모션_가능_재고보다_적게_주문_추가_출력() {
        assertSimpleTest(() -> {
            runException("[콜라-5]", "Y", "N");
            assertThat(output()).contains("현재 콜라은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        });
    }

    @Test
    void 프로모션_가능_재고보다_적게_주문_추가_가격() {
        assertSimpleTest(() -> {
            runException("[콜라-5]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains("내실돈4,000");
        });
    }

    @Test
    void 프로모션_가능_재고보다_적게_주문_추가_안할때_가격() {
        assertSimpleTest(() -> {
            runException("[콜라-8]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains("내실돈6,000");
        });
    }

    @Test
    void 프로모션_가능_재고보다_많이_주문_삭제() {
        assertSimpleTest(() -> {
            runException("[콜라-11]", "Y", "N");
            assertThat(output()).contains("현재 콜라 2개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        });
    }

    @Test
    void 프로모션_가능_재고보다_많이_주문_삭제_가격() {
        assertSimpleTest(() -> {
            runException("[콜라-11]", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains("내실돈8,000");
        });
    }

    @Test
    void 프로모션_가능_재고보다_많이_주문_삭제_안할때_가격() {
        assertSimpleTest(() -> {
            runException("[콜라-11]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains("내실돈6,000");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
