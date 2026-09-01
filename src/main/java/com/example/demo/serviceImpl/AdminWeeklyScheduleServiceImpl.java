package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Schedule;
import com.example.demo.model.WeeklySchedule;
import com.example.demo.model.WeeklyScheduleDate;
import com.example.demo.payload.WeeklyFilter;
import com.example.demo.payload.WeeklyFilter2;
import com.example.demo.repository.AdminScheduleRepo;
import com.example.demo.repository.AdminWeeklyScheduleRepo;
import com.example.demo.repository.WeekScheduleDateRepo;
import com.example.demo.service.AdminWeeklyScheduleService;

@Service
public class AdminWeeklyScheduleServiceImpl implements AdminWeeklyScheduleService {

	@Autowired
	AdminWeeklyScheduleRepo weeklyScheduleRepo;

	@Autowired
	AdminScheduleRepo dailyScheduleRepo;

	@Autowired
	WeekScheduleDateRepo weekScheduleDateRepo;

	// APi Chnage
	@Override
	public WeeklySchedule createSchedule(WeeklySchedule weeklySchedule) {

		try {
			weeklySchedule = weeklyScheduleRepo.save(weeklySchedule);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return weeklySchedule;
	}

	@Override
	public List<WeeklySchedule> getAllWeeklyScheduleList() {
		// List<WeeklySchedule> list =
		// weeklyScheduleRepo.findAllByStatus(ConstantVar.ONE);
		List<WeeklySchedule> list = weeklyScheduleRepo.findAllByOrderByIdDesc();
		return list;
	}

	// APi Change
	@Override
	public WeeklySchedule getScheduleById(Integer id) {
		Optional<WeeklySchedule> weeklySchd = null;
		try {
			// weeklySchd = weeklyScheduleRepo.findById(id);
			weeklySchd = weeklyScheduleRepo.findByIdOrderByWeeklyScheduleDateDateDesc(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		return weeklySchd.get();

		// List<WeeklyScheduleDate> dateList =
		// weekScheduleDateRepo.findByWeeklyScheduleId(list.getId());
		// for (WeeklyScheduleDate details : dateList) {
		// List<Schedule> scheduleList = new ArrayList<>();
		// for (Schedule schedule :
		// dailyScheduleRepo.findByWeeklyScheduleDateId(details.getId())) {
		//// schedule.getWeeklyScheduleDate().setWeeklySchedule(null);
		// schedule.setWeeklyScheduleDate(null);
		// scheduleList.add(schedule);
		// }
		// //
		// details.setSchedule(dailyScheduleRepo.findByWeeklyScheduleDateId(details.getId()));
		//// details.setSchedule(scheduleList);
		//// details.setWeeklySchedule(null);
		// dateListResponse.add(details);
		// }
		// list.setWeeklyScheduleDate(dateListResponse);
		// return list;

	}

	@Override
	public WeeklySchedule updateWeeklySchedule(WeeklySchedule weeklySchedule) {
		WeeklySchedule schdl = null;
		CopyOnWriteArrayList<WeeklyScheduleDate> wchdte = new CopyOnWriteArrayList<WeeklyScheduleDate>();
		CopyOnWriteArrayList<Schedule> schdList = new CopyOnWriteArrayList<Schedule>();

		try {
			if (weeklySchedule != null && weeklySchedule.getId() != null) {
				Optional<WeeklySchedule> sch = weeklyScheduleRepo.findById(weeklySchedule.getId());
				if (sch.isPresent()) {

					schdl = sch.get();

					if (weeklySchedule.getWeekStartDate() != null) {
						schdl.setWeekStartDate(weeklySchedule.getWeekStartDate());
					}
					if (weeklySchedule.getWeekEndDate() != null) {
						schdl.setWeekEndDate(weeklySchedule.getWeekEndDate());
					}
					if (weeklySchedule.getWeeklyScheduleDate() != null) {
						for (WeeklyScheduleDate wdt : weeklySchedule.getWeeklyScheduleDate()) {
							if (wdt != null && wdt.getId() != null) {
								Optional<WeeklyScheduleDate> weekschDate = weekScheduleDateRepo.findById(wdt.getId());
								if (weekschDate.isPresent()) {
									weekschDate.get().setUpdated_at(wdt.getUpdated_at() != null ? wdt.getUpdated_at()
											: weekschDate.get().getUpdated_at());
									// weekschDate.get().setStatus(
									// wdt.getStatus() != null ? wdt.getStatus() : weekschDate.get().getStatus());
									if (wdt.getDailySchedule() != null) {
										for (Schedule schd : wdt.getDailySchedule()) {
											Optional<Schedule> schdOld = null;
											if (schd != null && schd.getId() != null) {
												schdOld = dailyScheduleRepo.findById(schd.getId());
												if (schdOld.isPresent()) {
													schdOld.get()
															.setEndTime(schd.getEndTime() != null ? schd.getEndTime()
																	: schdOld.get().getEndTime());

													schdOld.get().setInstructor(
															schd.getInstructor() != null ? schd.getInstructor()
																	: schdOld.get().getInstructor());
													schdOld.get()
															.setLession(schd.getLession() != null ? schd.getLession()
																	: schdOld.get().getLession());
													schdOld.get().setPeriod(schd.getPeriod() != null ? schd.getPeriod()
															: schdOld.get().getPeriod());
													schdOld.get().setPlace(schd.getPlace() != null ? schd.getPlace()
															: schdOld.get().getPlace());
													schdOld.get().setStartTime(
															schd.getStartTime() != null ? schd.getStartTime()
																	: schdOld.get().getStartTime());
													schdOld.get()
															.setSubject(schd.getSubject() != null ? schd.getSubject()
																	: schdOld.get().getSubject());
													schdOld.get().setType(schd.getType() != null ? schd.getType()
															: schdOld.get().getType());
													schdOld.get().setUpdated_at(
															schd.getUpdated_at() != null ? schd.getUpdated_at()
																	: schdOld.get().getUpdated_at());
													// Schedule schdsve = dailyScheduleRepo.save(schdOld.get());
													schdList.add(schdOld.get());
												}

											} else {
												schdList.add(schd);

											}

										}
									}
									weekschDate.get().setDailySchedule(schdList);
									wchdte.add(weekschDate.get());
									// weekScheduleDateRepo.save(wchdte);
								}
							} else {
								wchdte.add(wdt);
							}
						}

					}
				}
				schdl.setWeeklyScheduleDate(wchdte);
				schdl = weeklyScheduleRepo.save(schdl);
			}
		} catch (Exception ex) {
			// TODO: handle exception
			schdl = null;
			ex.printStackTrace();
			// throw new NullPointerHandle(ConstantMessage.INTERNAL_SERVER, ex);
		}
		return schdl;
		// if (null != weeklySchedule.getDailySchedule() &&
		// !weeklySchedule.getDailySchedule().isEmpty()) {
		// for (Schedule dailySchd : weeklySchedule.getDailySchedule()) {
		// if (dailySchd != null) {
		// Optional<Schedule> schold = dailyScheduleRepo.findById(dailySchd.getId());
		//
		// if (schold.isPresent()) {
		// schold.get().setInstructer(
		// dailySchd.getInstructer() != null ? dailySchd.getInstructer() : "");
		// // set all value using setter using ternary operator
		// schdlList.add(schold.get());
		// } else {
		// schdlList.add(dailySchd);
		// }
		// }
		//
		// }
		// }

	}

	@Override
	public List<Schedule> getScheduleList() {
		// List<Schedule> response = dailyScheduleRepo.findAllByOrderByIdDesc();
		// return response;
		return null;
	}

	@Override
	public WeeklySchedule getWeeklyScheduleByWeek(Long id) {
		WeeklySchedule weekSchd = null;
		try {
			weekSchd = weeklyScheduleRepo.findByWeekId(id);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			weekSchd = null;
		}
		return weekSchd;
	}

	@Override
	public List<WeeklySchedule> getWeeklyScheduleDateByDate(WeeklyFilter filters) {
		// TODO Auto-generated method stub

		List<WeeklySchedule> weekSchd = null;
		try {

			// weekSchd = weekScheduleDateRepo.findByDateOrderByIdDesc(dt);
			weekSchd = weeklyScheduleRepo
					.WeekIdAndTermIdAndSessionTermIdAndYearAndBattalianIdAndWeeklyScheduleDateDateOrderByIdDesc(

							filters.getWeekId(), filters.getTermId(), filters.getTermSeasonId(), filters.getYear(),
							filters.getBattalianId(), filters.getDt());

			// System.out.println("inputDate " + filters.getDt());

			// DateTimeFormatter f = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z
			// uuuu").withLocale(Locale.US);
			// ZonedDateTime zdt = ZonedDateTime.parse(filters.getDt().toString(), f);
			// LocalDate ld = zdt.toLocalDate();
			// DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			// String output = ld.format(fLocalDate);
			// // System.out.println( "input: " + input );
			// System.out.println("zdt: " + zdt);
			// System.out.println("ld: " + ld);
			// System.out.println("output: " + output);

			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy",
			// Locale.ENGLISH);
			// LocalDate date = LocalDate.parse(filters.getDt().toString(), formatter);
			// System.out.println(" abc " + date); // 2010-01-02

			// DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			// DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// Date date = (Date) formatter.parse(filters.getDt().toString());
			// System.out.println("abc " + date);

			// String input = "Mon Jun 18 00:00:00 IST 2012";
			// DateTimeFormatter f = DateTimeFormatter.ofPattern( "E MMM dd HH:mm:ss z uuuu"
			// ).withLocale( Locale.US );
			// ZonedDateTime zdt = ZonedDateTime.parse( input , f );
			// LocalDate ld = date.toLocalDate();

			// DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );
			// String output = ld.format( fLocalDate) ;

			for (WeeklySchedule weeklySchedule : weekSchd) {
				if (weeklySchedule != null) {

					for (WeeklyScheduleDate weekDate : weeklySchedule.getWeeklyScheduleDate()) {

						// System.out.println("OutputDate " + weekDate.getDate());
						// System.out.println("OutputDate " + weekDate.getDate().toString());
						// System.out.println("OutputDate " + ld.toString());
						// System.out.println("OutputDate " + weekDate.getDailySchedule());
						java.util.Date utilDate = new java.util.Date(weekDate.getDate().getTime());
						System.out.println("inputDate  " + filters.getDt());
						System.out.println("new date util " + utilDate);
						if (weekDate != null) {

							if (weekDate.getDate().equals(utilDate) && weekDate.getDailySchedule() != null) {
								return weekSchd;
							} else {
								weekSchd.remove(weeklySchedule); //
								// list.remove(weekDate);

								return weekSchd;
							}

							// return weekSchd;
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			weekSchd = null;
		}
		return weekSchd;

	}

	@Override
	public List<WeeklySchedule> getCurrentWeekSchedule(WeeklyFilter filters) {
		List<WeeklySchedule> weekSchd = null;
		try {
			// weekSchd =
			// weeklyScheduleRepo.findByWeekIdOrTermIdOrSessionTermIdOrYearOrBattalianIdOrderByIdDesc(
			weekSchd = weeklyScheduleRepo.findByWeekIdAndTermIdAndSessionTermIdAndYearAndBattalianIdOrderByIdDesc(

					filters.getWeekId(), filters.getTermId(), filters.getTermSeasonId(), filters.getYear(),
					filters.getBattalianId());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			weekSchd = null;
		}
		return weekSchd;
	}

	@Override
	public WeeklyScheduleDate getWeeklyScheduleDateByDate1(WeeklyFilter filters) {
		// TODO Auto-generated method stub
		List<WeeklySchedule> weekSchd = null;
		WeeklyScheduleDate weekDateData = null;
		try {

			weekSchd = weeklyScheduleRepo
					.WeekIdAndTermIdAndSessionTermIdAndYearAndBattalianIdAndWeeklyScheduleDateDateOrderByIdDesc(

							filters.getWeekId(), filters.getTermId(), filters.getTermSeasonId(), filters.getYear(),
							filters.getBattalianId(), filters.getDt());

			for (WeeklySchedule weeklySchedule : weekSchd) {
				if (weeklySchedule != null) {

					for (WeeklyScheduleDate weekDate : weeklySchedule.getWeeklyScheduleDate()) {
						if (weekDate != null) {
							java.util.Date DatabaseDate = new java.util.Date(weekDate.getDate().getDate());
							java.util.Date FiltersDate = new java.util.Date(filters.getDt().getDate());

							System.out.println("database " + DatabaseDate);
							System.out.println("filter " + FiltersDate);

							if (DatabaseDate.equals(FiltersDate) && weekDate.getDailySchedule() != null) {
								return weekDateData = weekDate;
								// return weekSchd;
							}
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			weekSchd = null;
		}
		return weekDateData;
	}

	@Override
	public WeeklyScheduleDate getWeeklyScheduleDateByDate2(WeeklyFilter2 filter2) {
		List<WeeklySchedule> weekSchd = null;
		WeeklyScheduleDate weekDateData = null;

		try {

			weekSchd = weeklyScheduleRepo.TermIdAndBattalianIdAndWeeklyScheduleDateDate(filter2.getTermId(),
					filter2.getBattalianId(), filter2.getDt());

			for (WeeklySchedule weeklySchedule : weekSchd) {
				if (weeklySchedule != null) {

					for (WeeklyScheduleDate weekDate : weeklySchedule.getWeeklyScheduleDate()) {
						if (weekDate != null) {
							java.util.Date DatabaseDate = new java.util.Date(weekDate.getDate().getDate());
							java.util.Date FiltersDate = new java.util.Date(filter2.getDt().getDate());

							System.out.println("database " + DatabaseDate);
							System.out.println("filter " + FiltersDate);

							if (DatabaseDate.equals(FiltersDate) && weekDate.getDailySchedule() != null) {
								return weekDateData = weekDate;
								// return weekSchd;
							}
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			weekSchd = null;
		}
		return weekDateData;

	}

}
