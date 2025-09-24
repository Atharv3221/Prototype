import { AnimatePresence, motion } from 'framer-motion';
import {
  BarChart3,
  Bell,
  FileText,
  Home,
  Settings,
  TrendingUp,
  Users,
  X
} from 'lucide-react';
import React, { useState } from 'react';
import { cn } from '../utils/cn';

const menuItems = [
  { icon: Home, label: 'Dashboard', active: true },
  { icon: BarChart3, label: 'Analytics' },
  { icon: Users, label: 'Users' },
  { icon: FileText, label: 'Reports' },
  { icon: TrendingUp, label: 'Progress Report' },
  { icon: Bell, label: 'Notifications' },
  { icon: Settings, label: 'Settings' },
];

interface SidebarProps {
  isOpen: boolean;
  setIsOpen: (open: boolean) => void;
}

export const Sidebar: React.FC<SidebarProps> = ({ isOpen, setIsOpen }) => {
  const [hoveredItem, setHoveredItem] = useState<number | null>(null);

  const sidebarVariants = {
    open: { width: '16rem', opacity: 1 },
    closed: { width: '5rem', opacity: 0.9 },
  };

  const itemVariants = {
    open: { x: 0, opacity: 1 },
    closed: { x: -10, opacity: 0 },
  };

  return (
    <>
      {/* Mobile Overlay */}
      <AnimatePresence>
        {isOpen && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            onClick={() => setIsOpen(false)}
            className="fixed inset-0 bg-black/50 z-40 lg:hidden"
          />
        )}
      </AnimatePresence>

      {/* Sidebar */}
      <motion.aside
        variants={sidebarVariants}
        animate={isOpen ? 'open' : 'closed'}
        transition={{ duration: 0.3, ease: 'easeInOut' }}
        className={cn(
          'fixed lg:sticky top-0 left-0 h-screen bg-white/95 dark:bg-slate-900/95 backdrop-blur-xl border-r border-slate-200/50 dark:border-slate-700/50 z-50 flex flex-col',
          'lg:translate-x-0',
          isOpen ? 'translate-x-0' : '-translate-x-full lg:translate-x-0'
        )}
      >
        {/* Header */}
        <div className="p-6 border-b border-gray-200/50 dark:border-gray-700/50">
          <div className="flex items-center justify-between">
            <motion.div
              initial={false}
              animate={{ opacity: isOpen ? 1 : 0 }}
              className="flex items-center gap-3"
            >
              <div className="w-8 h-8 rounded-lg bg-gradient-to-tr from-cyan-500 to-blue-500 flex items-center justify-center shadow-lg shadow-cyan-500/25">
                <BarChart3 className="w-5 h-5 text-white" />
              </div>
              <AnimatePresence>
                {isOpen && (
                  <motion.span
                    initial={{ opacity: 0, x: -10 }}
                    animate={{ opacity: 1, x: 0 }}
                    exit={{ opacity: 0, x: -10 }}
                    className="text-xl font-bold bg-gradient-to-r from-cyan-600 to-blue-600 bg-clip-text text-transparent"
                  >
                    Jharkhand Govt
                  </motion.span>
                )}
              </AnimatePresence>
            </motion.div>
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="lg:hidden p-1 hover:bg-slate-100 dark:hover:bg-slate-800 rounded-lg transition-colors"
              aria-label="Close sidebar"
            >
              <X className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Navigation */}
        <nav className="flex-1 p-4 space-y-2">
          {menuItems.map((item, index) => (
            <motion.button
              key={item.label}
              onHoverStart={() => setHoveredItem(index)}
              onHoverEnd={() => setHoveredItem(null)}
              className={cn(
                'w-full flex items-center gap-3 p-3 rounded-xl transition-all duration-200 text-left group',
                item.active 
                  ? 'bg-gradient-to-r from-cyan-500 to-blue-500 text-white shadow-lg shadow-cyan-500/25' 
                  : 'text-slate-600 dark:text-slate-300 hover:bg-slate-100/80 dark:hover:bg-slate-800/80'
              )}
              whileHover={{ x: 5 }}
              whileTap={{ scale: 0.98 }}
            >
              <item.icon className={cn(
                'w-5 h-5 transition-transform duration-200',
                hoveredItem === index && 'scale-110',
                item.active ? 'text-white' : 'text-slate-500 group-hover:text-cyan-500'
              )} />
              <AnimatePresence>
                {isOpen && (
                  <motion.span
                    variants={itemVariants}
                    initial="closed"
                    animate="open"
                    exit="closed"
                    className="font-medium"
                  >
                    {item.label}
                  </motion.span>
                )}
              </AnimatePresence>
              {item.active && (
                <motion.div
                  layoutId="activeTab"
                  className="absolute right-3 w-2 h-2 bg-white rounded-full"
                />
              )}
            </motion.button>
          ))}
        </nav>

        {/* User Profile */}
        <div className="p-4 border-t border-slate-200/50 dark:border-slate-700/50">
          <motion.div
            className="flex items-center gap-3 p-3 rounded-xl hover:bg-slate-100/80 dark:hover:bg-slate-800/80 transition-colors cursor-pointer"
            whileHover={{ scale: 1.02 }}
          >
            <div className="w-8 h-8 rounded-full bg-gradient-to-tr from-cyan-400 to-blue-400 flex items-center justify-center text-white font-semibold text-sm shadow-lg shadow-cyan-400/25">
              AK
            </div>
            <AnimatePresence>
              {isOpen && (
                <motion.div
                  variants={itemVariants}
                  initial="closed"
                  animate="open"
                  exit="closed"
                  className="flex-1 min-w-0"
                >
                  <p className="text-sm font-medium text-slate-900 dark:text-slate-100 truncate">Aditya Kumar</p>
                  <p className="text-xs text-slate-500 dark:text-slate-400 truncate">Aditya@dev.in</p>
                </motion.div>
              )}
            </AnimatePresence>
          </motion.div>
        </div>
      </motion.aside>
    </>
  );
};