package kr.ac.mju.hanmaeum.utils.subway;

import java.util.ArrayList;

/**
 * Created by Youthink on 2017-01-22.
 */

public class SearchSTNBySubwayLineService {

    private _SearchSTNBySubwayLineService SearchSTNBySubwayLineService;

    public class _SearchSTNBySubwayLineService {

        private long list_total_count;
        private RESULT_CODE RESULT;
        private ArrayList<Subway> row;

        public class RESULT_CODE {
            private String CODE;
            private String MESSAGE;

            public RESULT_CODE(String CODE, String MESSAGE) {
                this.CODE = CODE;
                this.MESSAGE = MESSAGE;
            }

            public String getCODE() {
                return CODE;
            }

            public void setCODE(String CODE) {
                this.CODE = CODE;
            }

            public String getMESSAGE() {
                return MESSAGE;
            }

            public void setMESSAGE(String MESSAGE) {
                this.MESSAGE = MESSAGE;
            }
        }

        @Override public String toString() {
            return "SearchSTNBySubwayLineService{" +
                    "list_total_count=" + list_total_count +
                    ", RESULT=" + RESULT +
                    ", row=" + row +
                    '}';
        }

        public _SearchSTNBySubwayLineService(long list_total_count, RESULT_CODE RESULT, ArrayList<Subway> row) {
            this.list_total_count = list_total_count;
            this.RESULT = RESULT;
            this.row = row;
        }

        public ArrayList<Subway> getRow() {
            return row;
        }

        public void setRow(ArrayList<Subway> row) {
            this.row = row;
        }

        public RESULT_CODE getRESULT() {
            return RESULT;
        }

        public void setRESULT(RESULT_CODE RESULT) {
            this.RESULT = RESULT;
        }

        public long getList_total_count() {
            return list_total_count;
        }

        public void setList_total_count(long list_total_count) {
            this.list_total_count = list_total_count;
        }
    }

    public SearchSTNBySubwayLineService(_SearchSTNBySubwayLineService searchSTNBySubwayLineService) {
        SearchSTNBySubwayLineService = searchSTNBySubwayLineService;
    }

    public ArrayList<Subway> getSubway(){
        return SearchSTNBySubwayLineService.getRow();
    }

    public _SearchSTNBySubwayLineService getSearchSTNBySubwayLineService() {
        return SearchSTNBySubwayLineService;
    }

    public void setSearchSTNBySubwayLineService(_SearchSTNBySubwayLineService searchSTNBySubwayLineService) {
        SearchSTNBySubwayLineService = searchSTNBySubwayLineService;
    }
}
