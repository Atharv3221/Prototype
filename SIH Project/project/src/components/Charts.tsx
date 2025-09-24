import React from 'react';
import { motion } from 'framer-motion';
import {
  AreaChart,
  Area,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
} from 'recharts';
import { Card } from './ui/Card';

const revenueData = [
  { name: 'Jan', value: 4_00_000, previous: 2_40_000 },
  { name: 'Feb', value: 3_00_000, previous: 1_39_800 },
  { name: 'Mar', value: 2_00_000, previous: 9_80_000 },
  { name: 'Apr', value: 2_78_000, previous: 3_90_800 },
  { name: 'May', value: 1_89_000, previous: 4_80_000 },
  { name: 'Jun', value: 2_39_000, previous: 3_80_000 },
  { name: 'Jul', value: 3_49_000, previous: 4_30_000 },
];

const userActivityData = [
  { name: 'Mon', users: 12_000 },
  { name: 'Tue', users: 19_000 },
  { name: 'Wed', users: 8_000 },
  { name: 'Thu', users: 15_000 },
  { name: 'Fri', users: 20_000 },
  { name: 'Sat', users: 17_000 },
  { name: 'Sun', users: 13_000 },
];

const deviceData = [
  { name: 'Desktop', value: 45, color: '#06b6d4' },
  { name: 'Mobile', value: 35, color: '#3b82f6' },
  { name: 'Tablet', value: 20, color: '#10b981' },
];

export const RevenueChart: React.FC = () => {
  return (
    <Card className="p-6 col-span-2">
      <div className="flex items-center justify-between mb-6">
        <div>
          <h3 className="text-lg font-semibold text-slate-900 dark:text-slate-100">
            Report Overview
          </h3>
          <p className="text-sm text-slate-600 dark:text-slate-400">
            Monthly report comparison
          </p>
        </div>
        <motion.div
          whileHover={{ scale: 1.05 }}
          className="px-3 py-1 bg-cyan-100 dark:bg-cyan-900/20 text-cyan-700 dark:text-cyan-300 rounded-full text-xs font-medium"
        >
          +12.5% vs last year
        </motion.div>
      </div>
      <div className="h-80">
        <ResponsiveContainer width="100%" height="100%">
          <AreaChart data={revenueData}>
            <defs>
              <linearGradient id="colorRevenue" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#06b6d4" stopOpacity={0.3} />
                <stop offset="95%" stopColor="#06b6d4" stopOpacity={0} />
              </linearGradient>
              <linearGradient id="colorPrevious" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#64748b" stopOpacity={0.3} />
                <stop offset="95%" stopColor="#64748b" stopOpacity={0} />
              </linearGradient>
            </defs>
            <CartesianGrid strokeDasharray="3 3" stroke="#64748b" opacity={0.1} />
            <XAxis 
              dataKey="name" 
              axisLine={false} 
              tickLine={false}
              tick={{ fill: '#64748b', fontSize: 12 }}
            />
            <YAxis 
              axisLine={false} 
              tickLine={false}
              tick={{ fill: '#64748b', fontSize: 12 }}
            />
            <Tooltip
              contentStyle={{
                backgroundColor: '#1e293b',
                border: 'none',
                borderRadius: '8px',
                color: '#f1f5f9'
              }}
            />
            <Area
              type="monotone"
              dataKey="previous"
              stroke="#64748b"
              strokeWidth={2}
              fillOpacity={1}
              fill="url(#colorPrevious)"
            />
            <Area
              type="monotone"
              dataKey="value"
              stroke="#06b6d4"
              strokeWidth={3}
              fillOpacity={1}
              fill="url(#colorRevenue)"
            />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    </Card>
  );
};

export const UserActivityChart: React.FC = () => {
  return (
    <Card className="p-6">
      <div className="mb-6">
        <h3 className="text-lg font-semibold text-slate-900 dark:text-slate-100">
          Issues Reported
        </h3>
        <p className="text-sm text-slate-600 dark:text-slate-400">
          Weekly Active Reports 
        </p>
      </div>
      <div className="h-64">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={userActivityData}>
            <CartesianGrid strokeDasharray="3 3" stroke="#64748b" opacity={0.1} />
            <XAxis 
              dataKey="name" 
              axisLine={false} 
              tickLine={false}
              tick={{ fill: '#64748b', fontSize: 12 }}
            />
            <YAxis 
              axisLine={false} 
              tickLine={false}
              tick={{ fill: '#64748b', fontSize: 12 }}
            />
            <Tooltip
              contentStyle={{
                backgroundColor: '#1e293b',
                border: 'none',
                borderRadius: '8px',
                color: '#f1f5f9'
              }}
            />
            <Bar 
              dataKey="users" 
              fill="url(#barGradient)" 
              radius={[4, 4, 0, 0]}
            />
            <defs>
              <linearGradient id="barGradient" x1="0" y1="0" x2="0" y2="1">
                <stop offset="0%" stopColor="#06b6d4" />
                <stop offset="100%" stopColor="#3b82f6" />
              </linearGradient>
            </defs>
          </BarChart>
        </ResponsiveContainer>
      </div>
    </Card>
  );
};

export const DeviceChart: React.FC = () => {
  return (
    <Card className="p-6">
      <div className="mb-6">
        <h3 className="text-lg font-semibold text-slate-900 dark:text-slate-100">
          Device Usage
        </h3>
        <p className="text-sm text-slate-600 dark:text-slate-400">
          Traffic by device type
        </p>
      </div>
      <div className="h-64">
        <ResponsiveContainer width="100%" height="100%">
          <PieChart>
            <Pie
              data={deviceData}
              cx="50%"
              cy="50%"
              innerRadius={40}
              outerRadius={80}
              paddingAngle={5}
              dataKey="value"
            >
              {deviceData.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={entry.color} />
              ))}
            </Pie>
            <Tooltip
              contentStyle={{
                backgroundColor: '#1e293b',
                border: 'none',
                borderRadius: '8px',
                color: '#f1f5f9',
                fontSize: '12px'
              }}
              formatter={(value: number) => [`${value}%`, '']}
            />
          </PieChart>
        </ResponsiveContainer>
      </div>
      <div className="space-y-2 mt-4">
        {deviceData.map((item, index) => (
          <div key={index} className="flex items-center justify-between">
            <div className="flex items-center gap-2">
              <div 
                className=" w-3 h-3 rounded-full" 
                style={{ backgroundColor: item.color }}
              />
              <span className="text-sm text-gray-600 dark:text-gray-400">
                {item.name}
              </span>
            </div>
            <span className="text-sm font-medium text-slate-600 dark:text-slate-300">
              {item.value}%
            </span>
          </div>
        ))}
      </div>
    </Card>
  );
};