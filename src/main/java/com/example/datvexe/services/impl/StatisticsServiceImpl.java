package com.example.datvexe.services.impl;

import com.example.datvexe.common.Status;
import com.example.datvexe.handler.CustomException;
import com.example.datvexe.models.*;
import com.example.datvexe.payloads.requests.StatisticsByAdminRequest;
import com.example.datvexe.payloads.responses.*;
import com.example.datvexe.repositories.*;
import com.example.datvexe.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    BusCompanyRepository busCompanyRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PackagesRepository packagesRepository;

    @Autowired
    TypeOfBusRepository typeOfBusRepository;

    @Autowired
    BusLineRepository busLineRepository;

    @Autowired
    BusRepository busRepository;

    @Override
    public float calculateAverageNumberOfEvaluation(Long busCompanyId) {
        BusCompany busCompany = busCompanyRepository.findBusCompanyById(busCompanyId);
        if (busCompany == null) throw new CustomException("404", "Khong tim thay nha xe!!!");
        // find evaluation have Bus Company id = busCompanyId
        List<Evaluation> evaluationList = evaluationRepository.findEvaluationsByBusCompany_Id(busCompany.getId());
        //set total start = 0
        int totalStart = 0;
        // this code to calculate total start in list evaluationList
        for (Evaluation evaluation : evaluationList) {
            totalStart = totalStart + evaluation.getStart();
        }
        // get number of evaluation
        int numberOfEvaluation = evaluationList.size();
        // calculate  average number of evaluation
        float result = Float.valueOf(totalStart) / numberOfEvaluation;
        return result;
    }

    @Override
    public StatisticsEvaluationResponse doStatisticsEvaluationResponse(Long busCompanyId) {
        // find bus company by id
        BusCompany busCompany = busCompanyRepository.findBusCompanyById(busCompanyId);
        if (busCompany == null) throw new CustomException("404", "Khong tim thay nha xe!!!");
        // find all evaluation of bus company
        List<Evaluation> evaluationList = evaluationRepository.findEvaluationsByBusCompany_Id(busCompany.getId());
        StatisticsEvaluationResponse statisticsEvaluationRequest = new StatisticsEvaluationResponse();
        // set array to set evaluation's start
        int[] start = new int[5];
        // count evaluation's start
        for (Evaluation evaluation : evaluationList) {
            start[evaluation.getStart() - 1] = start[evaluation.getStart() - 1] + 1;
        }
        // set value to evaluation's start from 1 to 5
        statisticsEvaluationRequest.setStart1(start[0]);
        statisticsEvaluationRequest.setStart2(start[1]);
        statisticsEvaluationRequest.setStart3(start[2]);
        statisticsEvaluationRequest.setStart4(start[3]);
        statisticsEvaluationRequest.setStart5(start[4]);
        // set number of evaluation
        statisticsEvaluationRequest.setNumberOfEvaluations(evaluationList.size());
        return statisticsEvaluationRequest;
    }

    @Override
    public List<AverageOfEvaluationResponse> getAllAverageOfEvaluation() {
        // find all bus company
        List<BusCompany> busCompanyList = busCompanyRepository.findAll();
        List<AverageOfEvaluationResponse> allAverageOfEvaluationResponseList = new ArrayList<AverageOfEvaluationResponse>();
        // set bus company id and average of evaluation for each averageOfEvaluation
        for (BusCompany busCompany : busCompanyList) {
            AverageOfEvaluationResponse averageOfEvaluationResponse = new AverageOfEvaluationResponse();
            // set average of evaluation by funtion calculateAverageOfEvaluation
            averageOfEvaluationResponse.setAverageOfEvaluation(calculateAverageNumberOfEvaluation(busCompany.getId()));
            averageOfEvaluationResponse.setIdBusCompany(busCompany.getId());
            // do not have evaluation then set averageOfEvaluation = 0
            if (Float.isNaN(averageOfEvaluationResponse.getAverageOfEvaluation()))
                averageOfEvaluationResponse.setAverageOfEvaluation(0F);
            allAverageOfEvaluationResponseList.add(averageOfEvaluationResponse);
        }
        return allAverageOfEvaluationResponseList;
    }

    @Override
    public List<StatisticsOderByAdminResponse> doStatisticsOderByAdmin(StatisticsByAdminRequest statisticsByAdminRequest) {
        // find all ticket valid
        List<Ticket> ticketList = ticketRepository.findTiketByStatusOrStatus(Status.ACTIVE, Status.COMPLETED);
        List<Ticket> ticketResultList = new ArrayList<Ticket>();
        // filter ticket valid with time
        for (Ticket ticket : ticketList) {
            if (ticket.getDateOder().getMonthValue() == statisticsByAdminRequest.getMonth() && ticket.getDateOder().getYear() == statisticsByAdminRequest.getYear()) {
                ticketResultList.add(ticket);
            }
        }
        // find all package valid
        List<Packages> packagesList = packagesRepository.findPackagesByStatusOrStatus(Status.ACTIVE, Status.COMPLETED);
        List<Packages> packagesResultList = new ArrayList<Packages>(); // this packageResultList to be final list of packages
        List<StatisticsOderByAdminResponse> statisticsOderByAdminResponseList = new ArrayList<StatisticsOderByAdminResponse>();
        // filter packages valid with time
        for (Packages eachPackages : packagesList) {
            if (eachPackages.getDateOder().getMonthValue() == statisticsByAdminRequest.getMonth() && eachPackages.getDateOder().getYear() == statisticsByAdminRequest.getYear()) {
                packagesResultList.add(eachPackages);
            }
        }
        // get all bus company
        List<BusCompany> busCompanyList = busCompanyRepository.findAll();
        float totalTicket = (float) ticketResultList.size(); // all tickets
        float totalPackages = (float) packagesResultList.size(); // all packages
        // calculate scale, all ticket and packages
        for (BusCompany busCompany : busCompanyList) {
            int tempTicket = 0;
            int tempPackages = 0;
            StatisticsOderByAdminResponse statisticsOderByAdminResponse = new StatisticsOderByAdminResponse();

            // set value for statisticsOderByAdminResponse
            statisticsOderByAdminResponse.setBusCompanyId(busCompany.getId());
            statisticsOderByAdminResponse.setNameBusCompany(busCompany.getNameBusCompany());
            // filter ticket with bus company id
            for (Ticket ticket : ticketResultList) {
                if (busCompany.getId() == ticket.getBusLine().getBus().getBusCompany().getId()) {
                    tempTicket = tempTicket + 1;
                }
            }
            // filter packages with bus company id
            for (Packages packagesCheck : packagesResultList) {
                if (busCompany.getId() == packagesCheck.getBusLine().getBus().getBusCompany().getId())
                    tempPackages = tempPackages + 1;
            }
            // set value for statisticsOderByAdminReponse
            statisticsOderByAdminResponse.setNumberOfTicket(tempTicket);
            statisticsOderByAdminResponse.setNumberOfPackages(tempPackages);
            if (tempTicket ==0)
                statisticsOderByAdminResponse.setScaleTicket(0); // to set no scaling
            else statisticsOderByAdminResponse.setScaleTicket(( tempTicket/ totalTicket)*100);

            if (tempPackages ==0)
                statisticsOderByAdminResponse.setScalePackages(0); // to set no scaling
            else statisticsOderByAdminResponse.setScalePackages(( tempPackages/ totalPackages)*100);
            // add statistics each bus company to all statistics bus companies
            statisticsOderByAdminResponseList.add(statisticsOderByAdminResponse);
        }
        return statisticsOderByAdminResponseList;
    }

    @Override
    public List<StatisticsRevenueByAdminResponse> getStatisticsRevenueByAdmin(StatisticsByAdminRequest statisticsByAdminRequest) {
        // find all ticket valid
        List<Ticket> ticketList = ticketRepository.findTiketByStatusOrStatus(Status.ACTIVE, Status.COMPLETED);
        List<Ticket> ticketResultList = new ArrayList<Ticket>();
        // filter ticket with time
        for (Ticket ticket : ticketList) {
            if (ticket.getDateOder().getMonthValue() == statisticsByAdminRequest.getMonth() && ticket.getDateOder().getYear() == statisticsByAdminRequest.getYear()) {
                ticketResultList.add(ticket);
            }
        }
        // find all packages valid
        List<Packages> packagesList = packagesRepository.findPackagesByStatusOrStatus(Status.ACTIVE, Status.COMPLETED);
        List<Packages> packagesResultList = new ArrayList<Packages>();
        // create list reponse
        List<StatisticsRevenueByAdminResponse> statisticsRevenueByAdminResponseList = new ArrayList<StatisticsRevenueByAdminResponse>();
        // filter package with time
        for (Packages packages : packagesList) {
            if (packages.getDateOder().getMonthValue() == statisticsByAdminRequest.getMonth() && packages.getDateOder().getYear() == statisticsByAdminRequest.getYear()) {
                packagesResultList.add(packages);
            }
        }
        // get all bus company
        List<BusCompany> busCompanyList = busCompanyRepository.findAll();

        for (BusCompany busCompany : busCompanyList) {
            int tempRevenue = 0;
            int totalRevenue =0;
            StatisticsRevenueByAdminResponse statisticsRevenueByAdminResponse = new StatisticsRevenueByAdminResponse();

            // set value for statisticsRevenueAdminResponse
            statisticsRevenueByAdminResponse.setBusCompanyId(busCompany.getId());
            statisticsRevenueByAdminResponse.setNameOfBusCompany(busCompany.getNameBusCompany());
            // filter tickets for each bus company
            for (Ticket ticket : ticketResultList) {
                if (busCompany.getId() == ticket.getBusLine().getBus().getBusCompany().getId()) {
                    tempRevenue = tempRevenue + ticket.getBusLine().getFare(); // with each ticket valid plus fare
                }
                totalRevenue = totalRevenue + ticket.getBusLine().getFare(); // this is fare for all tickets
            }
            for (Packages packagesCheck : packagesResultList) {
                if (busCompany.getId() == packagesCheck.getBusLine().getBus().getBusCompany().getId())
                    tempRevenue = tempRevenue + packagesCheck.getFee(); // with each packages valid plus fare
                totalRevenue = totalRevenue + packagesCheck.getFee(); // this is fare for all tickets
            }
            // set value for each reponse
            statisticsRevenueByAdminResponse.setRevenue(tempRevenue);
            statisticsRevenueByAdminResponse.setScaleRevenue(((float) tempRevenue/(float) totalRevenue)*100);
            // add reponse above to list reponse
            statisticsRevenueByAdminResponseList.add(statisticsRevenueByAdminResponse);
        }
        return statisticsRevenueByAdminResponseList;
    }

}

