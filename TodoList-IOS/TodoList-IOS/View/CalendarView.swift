//
//  CalendarView.swift
//  TodoList-IOS
//
//  Created by 윤서원 on 2022/01/19.
//

import SwiftUI
import Foundation

struct CalendarView: View {
    @State private var year: Int
    @State private var month: Int
    private var startOfDay: Int
    private var endOfPreviosMonthsDay: Int
    private var endOfDay: Int
    
    
    var body: some View {
        VStack(alignment: .center) {
            Text("\(String(year)) . \(month)")
                .font(.title)
                .padding(.bottom, 15.0)
            
            VStack {
                LazyVGrid(columns: CalendarView.columns) {
                    Group {
                        Text("일")
                        Text("월")
                        Text("화")
                        Text("수")
                        Text("목")
                        Text("금")
                        Text("토")
                    }
                }.padding(.bottom, 10)
                
                ForEach(0..<6) { row in
                    LazyVGrid(columns: CalendarView.columns) {
                        ForEach(0..<7) { col in
                            let day = 7*row + col + 2 - startOfDay
                            if day <= 0 {
                                CalendarCellView(day: endOfPreviosMonthsDay + day, isCurrentCell: false)
                            } else if day > endOfDay {
                                CalendarCellView(day: day - endOfDay, isCurrentCell: false)
                            } else {
                                CalendarCellView(day: day)
                            }
                        }
                    }
                    
                }
            }
            
        }
        .padding(.horizontal, 50)
    }
    
    static var columns = [GridItem](repeating: GridItem(.flexible()), count: 7)
    static var dayOffWeeks = ["일", "월", "화", "수", "목", "금", "토"]
    
    init() {
        let today = Date()
        
        year = Calendar.current.component(.year, from: today)
        month = Calendar.current.component(.month, from: today)
        
        let day = Calendar.current.component(.day, from: today)
        let weekday = Calendar.current.component(.day, from: today)
        startOfDay = abs(weekday - day) % 7
        if startOfDay == 0{
            startOfDay = 7
        }
        
        let previousMonthDate = Calendar.current.date(byAdding: .day, value: -day, to: today)!
        endOfPreviosMonthsDay = Calendar.current.component(.day, from: previousMonthDate)
        let startDate = Calendar.current.date(byAdding: .day, value: 1, to: previousMonthDate)!
        let nextMonth = Calendar.current.date(byAdding: .month, value: 1, to: startDate)!
        let endOfDate = Calendar.current.date(byAdding: .day, value: -1, to: nextMonth)!
        endOfDay = Calendar.current.component(.day, from: endOfDate)
    }
}

struct CalendarView_Previews: PreviewProvider {
    static var previews: some View {
        CalendarView()
    }
}
