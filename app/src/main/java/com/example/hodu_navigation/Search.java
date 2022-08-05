package com.example.hodu_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import java.util.List;



public class Search extends AppCompatActivity {
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private AutoCompleteTextView editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        editSearch = (AutoCompleteTextView) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getAdapter().getItem(position);

                Intent intent1 = new Intent(getApplicationContext(), Input.class);
                intent1.putExtra("str",selected_item);

                startActivity(intent1);
                //텍스트뷰에 출력
                //editSearch.setText(selected_item);



           }
        });

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        list.clear();
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            //list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else {
            // 리스트의 모든 데이터를 검색한다.
            for (int i = 0; i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }


    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList(){
        list.add("소요산");
        list.add("동두천");
        list.add("보산");
        list.add("동두천중앙");
        list.add("지행");
        list.add("덕정");
        list.add("덕계");
        list.add("양주");
        list.add("녹양");
        list.add("가능");
        list.add("의정부");
        list.add("회룡");
        list.add("망월사");
        list.add("도봉산");
        list.add("도봉");
        list.add("방학");
        list.add("창동");
        list.add("녹천");
        list.add("월계");
        list.add("광운대");
        list.add("석계");
        list.add("신이문");
        list.add("외대앞");
        list.add("회기");
        list.add("남영");
        list.add("용산");
        list.add("노량진");
        list.add("대방");
        list.add("신길");
        list.add("영등포");
        list.add("신도림");
        list.add("구로");
        list.add("구일");
        list.add("개봉");
        list.add("오류동");
        list.add("온수");
        list.add("역곡");
        list.add("소사");
        list.add("부천");
        list.add("중동");
        list.add("송내");
        list.add("부개");
        list.add("부평");
        list.add("백운");
        list.add("동암");
        list.add("간석");
        list.add("주안");
        list.add("도화");
        list.add("제물포");
        list.add("도원");
        list.add("동인천");
        list.add("인천");
        list.add("가산디지털단지");
        list.add("독산");
        list.add("금천구청");
        list.add("광명");
        list.add("석수");
        list.add("관악");
        list.add("안양");
        list.add("명학");
        list.add("금정");
        list.add("군포");
        list.add("당정");
        list.add("의왕");
        list.add("성균관대");
        list.add("화서");
        list.add("수원");
        list.add("세류");
        list.add("병점");
        list.add("서동탄");
        list.add("세마");
        list.add("오산대");
        list.add("오산");
        list.add("진위");
        list.add("송탄");
        list.add("서정리");
        list.add("지제");
        list.add("평택");
        list.add("성환");
        list.add("직산");
        list.add("두정");
        list.add("천안");
        list.add("봉명");
        list.add("쌍용");
        list.add("아산");
        list.add("배방");
        list.add("온양온천");
        list.add("신창");
        list.add("청량리");
        list.add("제기동");
        list.add("신설동");
        list.add("동묘앞");
        list.add("동대문");
        list.add("종로5가");
        list.add("종로3가");
        list.add("종각");
        list.add("시청");
        list.add("서울역");
        list.add("을지로입구");
        list.add("을지로3가");
        list.add("을지로4가");
        list.add("동대문역사문화공원");
        list.add("신당");
        list.add("상왕십리");
        list.add("왕십리");
        list.add("한양대");
        list.add("뚝섬");
        list.add("성수");
        list.add("용답");
        list.add("신답");
        list.add("용두");
        list.add("건대입구");
        list.add("구의");
        list.add("강변");
        list.add("잠실나루");
        list.add("잠실");
        list.add("잠실새내");
        list.add("종합운동장");
        list.add("삼성");
        list.add("선릉");
        list.add("역삼");
        list.add("강남");
        list.add("교대");
        list.add("서초");
        list.add("방배");
        list.add("사당");
        list.add("낙성대");
        list.add("서울대입구");
        list.add("봉천");
        list.add("신림");
        list.add("신대방");
        list.add("구로디지털단지");
        list.add("대림");
        list.add("도림천");
        list.add("양천구청");
        list.add("신정네거리");
        list.add("까치산");
        list.add("문래");
        list.add("영등포구청");
        list.add("당산");
        list.add("합정");
        list.add("홍대입구");
        list.add("신촌");
        list.add("이대");
        list.add("아현");
        list.add("충정로");
        list.add("지축");
        list.add("구파발");
        list.add("연신내");
        list.add("불광");
        list.add("녹번");
        list.add("홍제");
        list.add("무악재");
        list.add("독립문");
        list.add("경복궁");
        list.add("안국");
        list.add("충무로");
        list.add("동대입구");
        list.add("약수");
        list.add("금호");
        list.add("옥수");
        list.add("압구정");
        list.add("신사");
        list.add("잠원");
        list.add("고속터미널");
        list.add("남부터미널");
        list.add("양재");
        list.add("매봉");
        list.add("도곡");
        list.add("대치");
        list.add("학여울");
        list.add("대청");
        list.add("일원");
        list.add("수서");
        list.add("가락시장");
        list.add("경찰병원");
        list.add("오금");
        list.add("대화");
        list.add("주엽");
        list.add("정발산");
        list.add("마두");
        list.add("백석");
        list.add("대곡");
        list.add("화정");
        list.add("원당");
        list.add("원흥");
        list.add("삼송");
        list.add("선바위");
        list.add("경마공원");
        list.add("대공원");
        list.add("과천");
        list.add("정부과천청사");
        list.add("인덕원");
        list.add("평촌");
        list.add("범계");
        list.add("산본");
        list.add("수리산");
        list.add("대야미");
        list.add("반월");
        list.add("상록수");
        list.add("한대앞");
        list.add("중앙");
        list.add("고잔");
        list.add("초지");
        list.add("안산");
        list.add("신길온천");
        list.add("정왕");
        list.add("오이도");
        list.add("당고개");
        list.add("상계");
        list.add("노원");
        list.add("쌍문");
        list.add("수유");
        list.add("미아");
        list.add("미아사거리");
        list.add("길음");
        list.add("성신여대입구");
        list.add("한성대입구");
        list.add("혜화");
        list.add("명동");
        list.add("회현");
        list.add("숙대입구");
        list.add("삼각지");
        list.add("신용산");
        list.add("이촌");
        list.add("동작");
        list.add("총신대입구");
        list.add("남태령");
        list.add("방화");
        list.add("개화산");
        list.add("김포공항");
        list.add("송정");
        list.add("마곡");
        list.add("발산");
        list.add("우장산");
        list.add("화곡");
        list.add("신정");
        list.add("목동");
        list.add("오목교");
        list.add("양평");
        list.add("영등포시장");
        list.add("여의도");
        list.add("여의나루");
        list.add("마포");
        list.add("공덕");
        list.add("애오개");
        list.add("서대문");
        list.add("광화문");
        list.add("청구");
        list.add("신금호");
        list.add("행당");
        list.add("마장");
        list.add("답십리");
        list.add("장한평");
        list.add("군자");
        list.add("아차산");
        list.add("광나루");
        list.add("천호");
        list.add("강동");
        list.add("길동");
        list.add("굽은다리");
        list.add("명일");
        list.add("고덕");
        list.add("상일동");
        list.add("강일");
        list.add("미사");
        list.add("하남풍산");
        list.add("하남시청");
        list.add("하남검산단역");
        list.add("둔촌동");
        list.add("올림픽공원");
        list.add("방이");
        list.add("개롱");
        list.add("거여");
        list.add("마천");
        list.add("응암");
        list.add("역촌");
        list.add("독바위");
        list.add("구산");
        list.add("새절");
        list.add("증산");
        list.add("디지털미디어시티");
        list.add("월드컵경기장");
        list.add("마포구청");
        list.add("망원");
        list.add("상수");
        list.add("광흥창");
        list.add("대흥");
        list.add("효창공원앞");
        list.add("녹사평");
        list.add("이태원");
        list.add("한강진");
        list.add("버티고개");
        list.add("창신");
        list.add("보문");
        list.add("안암");
        list.add("고려대");
        list.add("월곡");
        list.add("상월곡");
        list.add("돌곶이");
        list.add("태릉입구");
        list.add("화랑대");
        list.add("봉화산");
        list.add("신내");
        list.add("장암");
        list.add("수락산");
        list.add("마들");
        list.add("중계");
        list.add("하계");
        list.add("공릉");
        list.add("먹골");
        list.add("중화");
        list.add("상봉");
        list.add("면목");
        list.add("사가정");
        list.add("용마산");
        list.add("중곡");
        list.add("어린이대공원");
        list.add("뚝섬유원지");
        list.add("청담");
        list.add("강남구청");
        list.add("학동");
        list.add("논현");
        list.add("반포");
        list.add("내방");
        list.add("남성");
        list.add("숭실대입구");
        list.add("상도");
        list.add("장승배기");
        list.add("신대방삼거리");
        list.add("보라매");
        list.add("신풍");
        list.add("남구로");
        list.add("철산");
        list.add("광명사거리");
        list.add("천왕");
        list.add("까치울");
        list.add("부천종합운동장");
        list.add("춘의");
        list.add("신중동");
        list.add("부천시청");
        list.add("상동");
        list.add("삼산체육관");
        list.add("굴포천");
        list.add("부평구청");
        list.add("암사");
        list.add("강동구청");
        list.add("몽촌토성");
        list.add("석촌");
        list.add("송파");
        list.add("문정");
        list.add("장지");
        list.add("복정");
        list.add("산성");
        list.add("남한산성입구");
        list.add("단대오거리");
        list.add("신흥");
        list.add("수진");
        list.add("모란");
        list.add("개화");
        list.add("공항시장");
        list.add("신방화");
        list.add("마곡나루");
        list.add("양천향교");
        list.add("가양");
        list.add("증미");
        list.add("등촌");
        list.add("염창");
        list.add("신목동");
        list.add("선유도");
        list.add("국회의사당");
        list.add("샛강");
        list.add("노들");
        list.add("흑석");
        list.add("구반포");
        list.add("신반포");
        list.add("사평");
        list.add("신논현");
        list.add("언주");
        list.add("선정릉");
        list.add("삼성중앙");
        list.add("봉은사");
        list.add("삼전");
        list.add("석촌고분");
        list.add("송파나루");
        list.add("한성백제");
        list.add("둔촌오륜");
        list.add("중앙보훈병원");
        list.add("판교");
        list.add("이매");
        list.add("삼동");
        list.add("경기광주");
        list.add("초월");
        list.add("곤지암");
        list.add("신둔도예촌");
        list.add("이천");
        list.add("부발");
        list.add("세종대왕릉");
        list.add("여주");
        list.add("서빙고");
        list.add("한남");
        list.add("응봉");
        list.add("중랑");
        list.add("망우");
        list.add("양원");
        list.add("구리");
        list.add("도농");
        list.add("양정");
        list.add("덕소");
        list.add("도심");
        list.add("팔당");
        list.add("운길산");
        list.add("양수");
        list.add("신원");
        list.add("국수");
        list.add("아신");
        list.add("오빈");
        list.add("원덕");
        list.add("용문");
        list.add("지평");
        list.add("서강대");
        list.add("가좌");
        list.add("수색");
        list.add("화전");
        list.add("강매");
        list.add("행신");
        list.add("능곡");
        list.add("곡산");
        list.add("백마");
        list.add("풍산");
        list.add("일산");
        list.add("탄현");
        list.add("야당");
        list.add("운정");
        list.add("금릉");
        list.add("금촌");
        list.add("월롱");
        list.add("파주");
        list.add("문산");
        list.add("임진강");
        list.add("갈매");
        list.add("별내");
        list.add("퇴계원");
        list.add("사릉");
        list.add("금곡");
        list.add("평내호평");
        list.add("천마산");
        list.add("마석");
        list.add("대성리");
        list.add("청평");
        list.add("상천");
        list.add("가평");
        list.add("굴봉산");
        list.add("백양리");
        list.add("강촌");
        list.add("김유정");
        list.add("남춘천");
        list.add("춘천");
        list.add("계양");
        list.add("검암");
        list.add("청라국제도시");
        list.add("영종");
        list.add("운서");
        list.add("공항화물청사");
        list.add("인천공항1터미널");
        list.add("인천공항2터미널");
        list.add("양촌");
        list.add("구래");
        list.add("마산");
        list.add("장기");
        list.add("운양");
        list.add("걸포북변");
        list.add("사우");
        list.add("풍무");
        list.add("고촌");
        list.add("소새울");
        list.add("시흥대야");
        list.add("신천");
        list.add("신현");
        list.add("시흥시청");
        list.add("시흥능곡");
        list.add("달미");
        list.add("선부");
        list.add("시우");
        list.add("원시");
        list.add("서울숲");
        list.add("압구정로데오");
        list.add("한티");
        list.add("구룡");
        list.add("개포동");
        list.add("대모산입구");
        list.add("가천대");
        list.add("태평");
        list.add("야탑");
        list.add("서현");
        list.add("수내");
        list.add("정자");
        list.add("미금");
        list.add("오리");
        list.add("죽전");
        list.add("보정");
        list.add("구성");
        list.add("신갈");
        list.add("기흥");
        list.add("상갈");
        list.add("청명");
        list.add("영통");
        list.add("망포");
        list.add("매탄권선");
        list.add("수원시청");
        list.add("매교");
        list.add("고색");
        list.add("오목천");
        list.add("어천");
        list.add("야목");
        list.add("사리");
        list.add("달월");
        list.add("월곶");
        list.add("소래포구");
        list.add("인천논현");
        list.add("호구포");
        list.add("남동인더스파크");
        list.add("원인재");
        list.add("연수");
        list.add("송도");
        list.add("인하대");
        list.add("숭의");
        list.add("신포");
        list.add("양재시민의숲");
        list.add("청계산입구");
        list.add("동천");
        list.add("수지구청");
        list.add("성복");
        list.add("상현");
        list.add("광교중앙");
        list.add("광교");
        list.add("강남대");
        list.add("지석");
        list.add("어정");
        list.add("동백");
        list.add("초당");
        list.add("삼가");
        list.add("시청·용인대");
        list.add("명지대");
        list.add("김량장");
        list.add("운동장·송담대");
        list.add("고진");
        list.add("보평");
        list.add("둔전");
        list.add("전대·에버랜드");
        list.add("북한산우이");
        list.add("솔밭공원");
        list.add("4.19민주묘지");
        list.add("가오리");
        list.add("화계");
        list.add("삼양");
        list.add("삼양사거리");
        list.add("솔샘");
        list.add("북한산보국문");
        list.add("정릉");
        list.add("발곡");
        list.add("범골");
        list.add("경전철의정부");
        list.add("의정부시청");
        list.add("흥선");
        list.add("의정부중앙");
        list.add("동오");
        list.add("새말");
        list.add("경기도청북부청사");
        list.add("효자");
        list.add("곤제");
        list.add("어룡");
        list.add("송산");
        list.add("탑석");
        list.add("귤현");
        list.add("박촌");
        list.add("임학");
        list.add("계산");
        list.add("경인교대입구");
        list.add("작전");
        list.add("갈산");
        list.add("부평시장");
        list.add("동수");
        list.add("부평삼거리");
        list.add("간석오거리");
        list.add("인천시청");
        list.add("예술회관");
        list.add("인천터미널");
        list.add("문학경기장");
        list.add("선학");
        list.add("신연수");
        list.add("동춘");
        list.add("동막");
        list.add("캠퍼스타운");
        list.add("테크노파크");
        list.add("지식정보단지");
        list.add("인천대입구");
        list.add("센트럴파크");
        list.add("국제업무지구");
        list.add("검단오류");
        list.add("왕길");
        list.add("검단사거리");
        list.add("마전");
        list.add("완정");
        list.add("독정");
        list.add("검바위");
        list.add("아시아드경기장");
        list.add("서구청");
        list.add("가정");
        list.add("가정중앙시장");
        list.add("석남");
        list.add("서부여성회관");
        list.add("인천가좌");
        list.add("가재울");
        list.add("주안국가산단");
        list.add("시민공원");
        list.add("석바위시장");
        list.add("석천사거리");
        list.add("모래내시장");
        list.add("만수");
        list.add("남동구청");
        list.add("인천대공원");
        list.add("운연");
        list.add("장기주차장");
        list.add("합동청사");
        list.add("파라다이스시티");
        list.add("워터파크");
        list.add("용유");
        list.add("산곡");
        list.add("송도달빛축제공원");
        list.add("가야대");
        list.add("장신대");
        list.add("연지공원");
        list.add("박물관");
        list.add("수로왕릉");
        list.add("봉황");
        list.add("부원");
        list.add("김해시청");
        list.add("인제대");
        list.add("김해대학");
        list.add("지내");
        list.add("불암");
        list.add("대사");
        list.add("평강");
        list.add("대저");
        list.add("등구");
        list.add("덕두");
        list.add("공항");
        list.add("서부산 유통지구");
        list.add("괘법 르네시떼");
        list.add("사상");
        list.add("노포");
        list.add("범어사");
        list.add("남산");
        list.add("두실");
        list.add("구서");
        list.add("장전");
        list.add("부산대");
        list.add("온천장");
        list.add("명륜");
        list.add("동래");
        list.add("연산");
        list.add("부전");
        list.add("서면");
        list.add("범내골");
        list.add("범일");
        list.add("좌천");
        list.add("부산진");
        list.add("초량");
        list.add("부산역");
        list.add("남포");
        list.add("자갈치");
        list.add("토성");
        list.add("동대신");
        list.add("서대신");
        list.add("대티");
        list.add("괴정");
        list.add("사하");
        list.add("당리");
        list.add("하단");
        list.add("신평");
        list.add("동매");
        list.add("장림");
        list.add("신장림");
        list.add("낫개");
        list.add("다대포항");
        list.add("다대포해수욕장");
        list.add("양산");
        list.add("남양산");
        list.add("부산대양산캠퍼스");
        list.add("호포");
        list.add("동원");
        list.add("율리");
        list.add("화명");
        list.add("수정");
        list.add("덕천");
        list.add("구명");
        list.add("구남");
        list.add("모라");
        list.add("모덕");
        list.add("덕포");
        list.add("감전");
        list.add("주례");
        list.add("냉정");
        list.add("개금");
        list.add("동의대");
        list.add("가야");
        list.add("부암");
        list.add("전포");
        list.add("국제금융센터·부산은행");
        list.add("문현");
        list.add("지게골");
        list.add("못골");
        list.add("대연");
        list.add("경성대·부경대");
        list.add("남천");
        list.add("금련산");
        list.add("광안");
        list.add("수영");
        list.add("민락");
        list.add("센텀시티");
        list.add("벡스코");
        list.add("해운대");
        list.add("장산");
        list.add("체육공원");
        list.add("강서구청");
        list.add("구포");
        list.add("숙등");
        list.add("남산정");
        list.add("만덕");
        list.add("미남");
        list.add("사직");
        list.add("거제");
        list.add("물만골");
        list.add("배산");
        list.add("망미");
        list.add("안평");
        list.add("윗반송");
        list.add("영산대");
        list.add("석대");
        list.add("반여농산물시장");
        list.add("금사");
        list.add("서동");
        list.add("명장");
        list.add("충렬사");
        list.add("낙민");
        list.add("수안");
        list.add("일광");
        list.add("기장");
        list.add("오시리아");
        list.add("신해운대");
        list.add("센텀");
        list.add("재송");
        list.add("부산원동");
        list.add("안락");
        list.add("거제해맞이");
        list.add("안심");
        list.add("각산");
        list.add("반야월");
        list.add("신기");
        list.add("율하");
        list.add("용계");
        list.add("방촌");
        list.add("해안");
        list.add("동촌(동촌유원지)");
        list.add("아양교(대구국제공항입구)");
        list.add("동구청(큰고개)");
        list.add("동대구역");
        list.add("칠성시장");
        list.add("대구역");
        list.add("중앙로");
        list.add("반월당");
        list.add("명덕(2.28민주운동기념회관)");
        list.add("영대병원");
        list.add("현충로");
        list.add("안지랑");
        list.add("대명");
        list.add("서부정류장");
        list.add("송현");
        list.add("월촌");
        list.add("상인");
        list.add("월배");
        list.add("진천");
        list.add("화원");
        list.add("설화명곡");
        list.add("영남대");
        list.add("임당");
        list.add("정평");
        list.add("사월");
        list.add("신매");
        list.add("고산");
        list.add("연호");
        list.add("담티");
        list.add("만촌");
        list.add("수성구청");
        list.add("범어");
        list.add("대구은행");
        list.add("경대병원");
        list.add("청라언덕");
        list.add("반고개");
        list.add("내당");
        list.add("두류");
        list.add("감삼");
        list.add("이곡");
        list.add("성서산업단지");
        list.add("계명대");
        list.add("강창");
        list.add("대실");
        list.add("다사");
        list.add("문양");
        list.add("용지");
        list.add("범물");
        list.add("지산");
        list.add("수성못(TBC)");
        list.add("황금");
        list.add("어린이회관");
        list.add("수성구민운동장");
        list.add("수성시장");
        list.add("대봉교");
        list.add("건들바위");
        list.add("남산(계명네거리)");
        list.add("서문시장(동산병원)");
        list.add("달성공원");
        list.add("북구청");
        list.add("원대");
        list.add("팔달시장");
        list.add("만평");
        list.add("공단");
        list.add("팔달");
        list.add("매천시장");
        list.add("매천");
        list.add("태전");
        list.add("구암(과학대.보건대입구)");
        list.add("칠곡운암");
        list.add("팔거(국립농관원.통계청)");
        list.add("학정");
        list.add("칠곡경대병원");
        list.add("반석");
        list.add("지족(침신대)");
        list.add("노은");
        list.add("현충원");
        list.add("구암");
        list.add("유성온천(충남대,목원대)");
        list.add("갑천");
        list.add("월평(KAIST)");
        list.add("갈마");
        list.add("정부청사");
        list.add("탄방");
        list.add("오룡");
        list.add("서대전네거리");
        list.add("중구청");
        list.add("대전역");
        list.add("대동(우송대)");
        list.add("판암");
        list.add("평동");
        list.add("도산");
        list.add("광주송정");
        list.add("송정공원");
        list.add("김대중컨벤션센터");
        list.add("상무");
        list.add("운천");
        list.add("쌍촌");
        list.add("농성");
        list.add("돌고개");
        list.add("양동시장");
        list.add("금남로5가");
        list.add("금남로4가");
        list.add("문화전당");
        list.add("남광주");
        list.add("학동.증심사입구");
        list.add("소태");
        list.add("녹동");
        list.add("탕정");


    }


}

