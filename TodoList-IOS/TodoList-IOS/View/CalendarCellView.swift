//
//  CalendarCellView.swift
//  TodoList-IOS
//
//  Created by 윤서원 on 2022/01/20.
//

import SwiftUI

struct CalendarCellView: View {
    var day: Int
    var completed: Int = 2
    var todoNumber: Int = 3
    var isCurrentCell: Bool = true
    
    private var color: Color {
        if isCurrentCell {
            return Color.black
        } else {
            return Color.gray
        }
    }
    
    var body: some View {
        VStack(alignment: .center) {
            Text("\(day)")
                .font(.title2)
                .fontWeight(.bold)
                .multilineTextAlignment(.center)
                .padding(.bottom, 0.5)
            
            Text("\(completed)/\(todoNumber)")
                .font(.subheadline)
                .multilineTextAlignment(.trailing)
            
        }.foregroundColor(color)
    }
}

struct CalendarCellView_Previews: PreviewProvider {
    static var previews: some View {
        CalendarCellView(day: 1, isCurrentCell: false)
    }
}
